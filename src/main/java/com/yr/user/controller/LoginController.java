package com.yr.user.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;
import com.yr.user.service.LoginService;
import com.yr.user.service.UserService;
import com.yr.util.SerializeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//SessionAttributes里的参数 names 这是一个字符串数组 里面应写需要存储到session中数据的名称
@SessionAttributes(value = {"user"}, types = {Integer.class})//这里指定一下 Session 才能获得指定的数据
@RequestMapping(value = "/u_user")
@Controller
public class LoginController {

    @Qualifier("loginServiceImpl")
    @Autowired
    private LoginService loginService;
    @Autowired
    private JedisManager jedisManager;
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @RequestMapping(value = "/userTables", method = RequestMethod.GET)//跳转登录页面
    public String logins() {
        return "index";
    }

    /*
       验证码*/
    @RequestMapping(value = "/userTable/getVerifyCode", method = RequestMethod.GET)
    public void Post(HttpServletResponse resp, HttpServletRequest req) throws Exception {
        // 设置响应的类型格式为图片格式
        resp.setContentType("image/jpeg");
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        // 自定义宽、高、字数和干扰线的条数
        IdentifyCode code = new IdentifyCode(100, 30, 6, 10);
        // 存入session
        HttpSession session = req.getSession();
        session.setAttribute("verifyCode", code.getCode());
        // 响应图片
        ServletOutputStream out = resp.getOutputStream();
        code.write(out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "/userTable/login", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String gotoIndex(User loginUser, String loginVerifyCode, HttpServletRequest request) {
        String str = "";
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");// 获取正确的验证码


        // if(loginVerifyCode != null && !"".equals(loginVerifyCode)){
        if (!loginVerifyCode.isEmpty()) {//判断验证码不等于空
            if ((loginVerifyCode.trim()).equalsIgnoreCase(verifyCode.trim())) {//先去掉验证码前后空格 在比较验证码是否正确
                List<User> loginUserNameList = loginService.queryLoginUserName(loginUser);//判断用户 账号是否存在
                if (!loginUserNameList.isEmpty() && loginUserNameList.size() >= 1) {//判断用户不能等于空 长度要大于1
                    User user = loginService.queryLoginUser(loginUser);//判断用户名 密码是否正确
                    if (!StringUtils.isEmpty(user)){
                        if (user.getStates() == 1) {
                            // 登录验证通过，把对象存进session
                            request.getSession().setAttribute("user", user);// 获取session对象并赋值
                            str = "{\"code\":1,\"msg\":\"登录成功\"}";// 账号登录成功
                        } else if (user.getStates() == 0) {
                            str = "{\"code\":2,\"msg\":\"账号未启用\"}";// 账号未启用
                        }
                    }else {
                        str = "{\"code\":3,\"msg\":\"账号/密码错误\"}";
                    }
                }else{
                    str = "{\"code\":4,\"msg\":\"账号无法登录\"}";//账号不存在
                }
            }else {
                str = "{\"code\":5,\"msg\":\"验证码错误\"}";// 验证码错误

            }
        } else {
            str = "{\"code\":6,\"msg\":\"请输入验证码\"}";// 验证码是空的
        }

        return str;

    }

    /**
     * 用Cookie实现七天免登陆
     * @param response
     * @param request
     */
    @RequestMapping(value ="/userTable/cookie",method = RequestMethod.GET)
    public void Cookie(HttpServletResponse response, HttpServletRequest request){
        /*
         * 如果是第一次登录，将用户名和密码作为cookie写到本地
         */
        String name = request.getParameter("name");
        String pwd = request.getParameter("password");
        User user = new User();
        String userInfo = user.toString();
        if(null!=name && !"".equals(name)){
            user.setName(name);
        }
        if(null!=pwd && !"".equals(pwd)){
            user.setPassword(pwd);
        }
        Cookie cookie = new Cookie("user",userInfo);
        cookie.setMaxAge(60*60*24*7);//设置7天效期
        cookie.setPath("/");//可在同一应用服务器内共享方法
        response.addCookie(cookie);//放松到客户段
        //凭这个Cookie就自动登录并不安全可以在服务端使用一个Session来管理用户。
        //当第一次登录成功后，就创建一个Session，并将用户的某些信息保存在Session
        HttpSession session = request.getSession();
        session.setAttribute("user", userInfo);
        session.setMaxInactiveInterval(3600*2);//2小时
        //但是当cookie关闭后，用于保存SessionID的JSESSIONID会消失(此时cookie并没有过期) ，所以得将JSESESSION持久化
        Cookie sessionId = new Cookie("JSESESSIONID",session.getId());
        sessionId.setMaxAge(2*60);//设置两小时
        sessionId.setPath("/");
        response.addCookie(sessionId);



        //当用户第二次登陆时，检测这个cookie是否存在
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie2 : cookies) {
            //如果存在这个cookie进行页面跳转
            if(cookie2.equals("user")){
                if(session.getAttribute("user")!=null){
                    try {
                        request.getRequestDispatcher("u_user/userTables").forward(request, response);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }else{
                    //跳转到登录页面
                }

            }
        }
        //如果使用上面的代码，即使浏览器关闭，在两小时之内，Web程序仍然可以自动登录。
        //如果我们自已加一个JSESSIONID Cookie，在第一次访问Web程序时，
        //HTTP响应头有两个JSESSIONID，但由于这两个JSESSIONID的值完全一样，没有任何影响
        //如果在响应头的Set-Cookie字段中有多个相同的Cookie，则按着path和name进行比较，如果这两个值相同，
        //则认为是同一个Cookie，最后一个出现的Cookie将覆盖前面相同的Cookie
    }

}

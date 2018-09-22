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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


//SessionAttributes里的参数 names 这是一个字符串数组 里面应写需要存储到session中数据的名称
@SessionAttributes(value = {"username"}, types = {Integer.class})//这里指定一下 Session 才能获得指定的数据
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
        return "redirect:/login";
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
        session.setAttribute("myCode", code.getCode());
        // 响应图片
        ServletOutputStream out = resp.getOutputStream();
        code.write(out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping(value = "/userTable/login", method = RequestMethod.GET)
    public String login(String s, HttpServletRequest request, String name, String password, String red, Map<String, Object> map, Integer states) {
        String des = s.toUpperCase(); // 得到界面输入的验证码//显示验证码
        String yzm = (String) request.getSession().getAttribute("rand");// 得到我们生成的正确的验证码
        System.out.println(yzm + "验证码");
        boolean sta = loginService.userstates(states);
        if (sta) {//判断用户是否离职
            boolean ll = loginService.All(name);
            if (ll) { //判断用户不为空
                boolean lls = loginService.Alls(password);
                if (lls) {//判断密码不为空
                    //if (!des.equals("")){//判断验证码不能为空
                    if (des.equals(yzm)) {//比较验证码
                        boolean an = loginService.getMatchCount(name, password);
                        if (an) {
                            HttpSession session = request.getSession();//得到用户和密码
                            request.getSession().setAttribute("username", name);//先把数据取出来 在存进去
                            session.setAttribute("users", loginService.getMatchCount(name, password));
                            map.put("err", 4);//验证通过

                            //别动我的        --熊定坤
                            //将用户的权限存入redis中
                            Subject subject = SecurityUtils.getSubject();//获取到用户对象
                            String userName = subject.getPrincipal().toString();
                            User user = userService.getByName(userName);//获取到user对象
                            List<String> roles = userService.getRoles(user.getId());//获得角色对象
                            Jedis jedis = jedisManager.getJedis();
                            //将角色字符串序列化后放入redis中
                            jedis.set("roles".getBytes(), SerializeUtil.serialize(roles));
                            List<Permission> permissions = userService.getPermissions(user.getId());
                            //将权限对象序列化后放入redis中
                            jedis.set("permissions".getBytes(), SerializeUtil.serialize(permissions));

                            return "redirect:/index";//通过返回到主页面
                        } else {
                            map.put("err", 2);//用户名或密码不正确
                            return "redirect:/u_user/userTables";
                        }
                    } else {
                        map.put("err", 3);//验证码不正确
                        return "redirect:/u_user/userTables";
                    }
                    // }
                } else {
                    System.out.println("密码不能为空");
                }
            } else {
                System.out.println("用户不能为空");
            }
        } else {
            map.put("err", 0);//用户以离职
            return "redirect:/u_user/userTables";
        }
        return null;
    }

    /**
     * 用Cookie实现七天免登陆
     * @param response
     * @param request
     */
    @RequestMapping(value ="/userTable/cookie",method = RequestMethod.GET)
    public void Cookie(HttpServletResponse response, HttpServletRequest request){
        String name="nihao";
        String password="nihao";
        try{
            Cookie user = new Cookie("user",name+"-"+password);
            user.setMaxAge(60*60*24*7);
            response.addCookie(user);
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for(int i=0;i<cookies.length;i++){
                    if(cookies[i].getName().equals("user")){
                        name=cookies[i].getValue().split("-")[0];
                        password=cookies[i].getValue().split("-")[1];
                        request.setAttribute("name",name);
                        request.setAttribute("password",password);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

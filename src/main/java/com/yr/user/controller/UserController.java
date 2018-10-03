package com.yr.user.controller;

import com.yr.common.service.UploadServer;
import com.yr.core.redis.JedisManager;
import com.yr.department.service.DepartmentService;
import com.yr.department.service.impl.DepartmentServiceImpl;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;
import com.yr.entitys.user.User;
import com.yr.user.service.LoginService;
import com.yr.user.service.RoleService;
import com.yr.user.service.UserService;
import com.yr.util.DateUtils;
import com.yr.util.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 操纵用户
 */
@Controller
@RequestMapping("/u_user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @Autowired
    private JedisManager jedisManager;
    @Autowired
    @Qualifier("departmentServiceImpl")
    private DepartmentService departmentService;
    @Autowired
    @Qualifier("roleServiceImpl")
    private RoleService roleServiceImpl;
    @Autowired
    private UploadServer uploadServerImpl;
    @Autowired
    private LoginService loginServiceImpl;

    /**
     * 如果检测到form表单提交带有id,直接将值存入request中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getAccount(@RequestParam(value="id",required=false) Integer id, Map<String, Object> map){
        if(id != null && id != 0){
            User user = userService.getById(id);
            map.put("user", user);
        }
    }

    /**
     * 跳转列表
     * @return
     */
    @RequestMapping(value = "/userTable",method = RequestMethod.GET)
    public String jumpList(){
        return "userList";
    }

    /**
     * 分页的形式查询user表的数据
     * @return List<User>
     */
    @RequestMapping(value="/userTable/list", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String query(UserBo userBo,Page<UserBo> page){//这里必须写bo类，因为userBo对象需要手动set进去
        //去空格
        User user = userBo.getUser();
        user.setName(user.getName().trim());
        user.setDepaCode(user.getDepaCode().trim());
        if(userBo.getMinAge() != null){
            userBo.setMinAge(Integer.valueOf(userBo.getMinAge().toString().trim()));
        }
        if(userBo.getMaxAge() != null){
            userBo.setMaxAge(Integer.valueOf(userBo.getMaxAge().toString().trim()));
        }

        String depaCode = userBo.getUser().getDepaCode();//这里可能是部门名可能是部门编号
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if(depaCode != null && !pattern.matcher(depaCode).matches()){//判断字符是否是数字,如果不是就去部门查询部门编号
            userBo.getUser().setDepaCode(departmentService.getDepaCode(depaCode));
        }
        page.setT(userBo);//将bo类置入对象
        String json = userService.query(page);//将list返回一个json字符串
        return json;
    }

    /**
     * 跳转添加页面
     * @return String
     */
    @RequestMapping(value="/userTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map){
        map.put("user", new User());//传入一个空的user对象
        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", "男");
        map1.put("0", "女");
        map.put("sexs", map1);
        Map<String,Object> departmentList = departmentService.querys();//查询所有的部门
        departmentList.put("-1","请选择");
        map.put("depaList",departmentList);
        //部门编号为键，名字为值
        //loginServiceImpl.
        return "userAU";
    }

    /**
     * 保存添加,先将图片保存到服务器，然后将用户的信息保存到数据库
     * @param user
     * @param filesCopy 传入路径
     * @param request
     * @return String
     */
    @RequestMapping(value="/userTable", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String saveAdd(User user, @RequestParam(value="filesCopy",required = false) String filesCopy, HttpServletRequest request){
        user.setPhoto(filesCopy);//替换掉原本的路径
        user.setAge(DateUtils.calculateTimeDifferenceByCalendar(user.getBirthday()));//计算当前时间-生日日期=现在年龄
        user.setStates(1);//默认是启用的
        user.setCreateTime(new Date());//获取当前时间
        user.setCreateEmp(((User)request.getSession().getAttribute("user")).getName());//获取当前用户名
        userService.add(user);
        return "{\"code\":1,\"msg\":\"保存成功\"}";
    }

    /**
     * 跳转修改
     * @return String
     */
    @RequestMapping(value="/userTable/{id}",method=RequestMethod.GET)
    public String jumpUpdate(@RequestParam(value="filesCopy",required = false) String filesCopy ,@PathVariable Integer id, Map<String, Object> map,UserBo userBo, Page<UserBo> page){
        page.setT(userBo);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", "男");
        map1.put("0", "女");
        map.put("sexs", map1);
        Map<String,Object> departmentList = departmentService.querys();//查询所有的部门
        departmentList.put("-1","请选择");
        map.put("depaList",departmentList);
        map.put("page", page);
        map.put("user", userService.getById(id));//根据id获取对象放入request中
        return "userAU";
    }

    /**
     * 保存修改
     * @return String
     */
    @RequestMapping(value="/userTable",method=RequestMethod.PUT, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String saveUpdate(@RequestParam(value="filesCopy",required = false) String filesCopy,User user, Page<UserBo> page, Map<String, Object> map, HttpServletRequest request){
        user.setPhoto(filesCopy);//替换掉原本的路径
        user.setUpdateTime(new Date());//获取修改当前时间
        user.setCreateEmp(((User)request.getSession().getAttribute("user")).getName());//获取修改用户
        userService.update(user);
        return "{\"code\":1,\"msg\":\"修改成功\"}";
    }

    /**
     * 删除用户
     * @return String
     */
    @RequestMapping(value="/userTable/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer[] id){
        userService.delete(id);//删除用户
        //删除图片用户
        for (Integer id1:id) {
            User user = userService.getById(id1);//根据用户id查询出对象
            FileUtils.delete(user.getPhoto());//获得用户的图片路径删除
        }
        return "{\"code\":1,\"msg\":\"删除成功\"}";
    }

    /**
     * 给用户赋角色
     * @param id
     * @param roleIds`
     */
    @RequestMapping(value="/userTable/setRoles", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String setRoles(Integer id,Integer[] roleIds,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");//获取session里存的用户对象
        userService.setRoles(id,roleIds,user);
        return "{\"code\":1,\"msg\":\"修改成功\"}";
    }

    /**
     * 实现静态头像上传，这里先将图片上传到服务器上
     * @param file
     * @param request
     * @param response
     * @return Map<String,Object>
     * @throws IOException
     */
    @RequestMapping(value="/userTable/upload",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadFile(@RequestParam("files") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = uploadServerImpl.upload(file);

        Map<String,Object> map = new HashMap<>();
        map.put("url",url);
        return map;
    }

    /**
     * 从redis缓存中拿到用户的id返回头像的字节流到jsp
     * 注意：session中一般用来存储用户的信息，因为能很好的监控到用户的行为
     *
     * 不需要
     */
    @RequestMapping(value="/userTable/icon")
    public void getIcon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");//从session取出对象
        //调用方法将流传出
        byte[] data = FileUtils.getFileFlow(user.getPhoto());//根据图片存在的路径获得byte流
        response.setContentType("image/png");//设置文件格式
        OutputStream stream = response.getOutputStream();//获得输出对象
        stream.write(data);//将图片以流的形式返回出去
        stream.flush();//刷新
        stream.close();//关闭
    }

    /**
     * 通过用户的id请求返回图像的字节流
     *
     * 不需要
     */
    @RequestMapping("/userTable/icons/{id}")
    public void getIcons(@PathVariable(value="id") Integer id ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.getById(id);//根据id获得user对象
        byte[] data = FileUtils.getFileFlow(user.getPhoto());//调用方法将流传出
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(data);//将图片以流的形式返回出去
        stream.flush();
        stream.close();
    }

    /**
     * 修改用户的状态
     * @param id
     */
    @RequestMapping(value="/userTable/updateState",method=RequestMethod.GET)
    @ResponseBody
    public String updateState(Integer id, Integer state){
        userService.updateState(id, state);
        return "{\"code\":1,\"msg\":\"修改成功\"}";
    }

    /**
     * 返回所有角色列表
     */
    @RequestMapping(value="/userTable/getRole", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getRole(){
        return roleServiceImpl.getRoleList();
    }

    /**
     * 根据id查看用户具有什么角色
     * @param uid
     * @return String
     */
    @RequestMapping(value="/userTable/getRoleById", produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getRole(Integer uid){
        return userService.getRole(uid);
    }

}

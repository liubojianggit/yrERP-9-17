package com.yr.user.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;
import com.yr.user.service.UserService;
import com.yr.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static String path = "C:/Users/Administrator/Desktop/photo";//图片路径

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
     * 分页的形式查询user表的数据
     * @return List<User>
     */
    @RequestMapping(value="/userTable", method = RequestMethod.GET)
    @ResponseBody
    public Page<UserBo> query(Page<UserBo> page){
        userService.query(page);
        return page;
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
        map1.put("2", "女");
        map1.put("3", "保密");
        map.put("sexs", map1);
        //部门编号为键，名字为值
        return "userAU";
    }

    /**
     * 保存添加,先将图片保存到本地，然后将用户的信息保存到数据库
     * @param user
     * @param filesCopy 传入路径
     * @param request
     * @return String
     */
    @RequestMapping(value="/userTable", method=RequestMethod.POST)
    public String saveAdd(User user, @RequestParam(value="filesCopy") String filesCopy, HttpServletRequest request){
        String phone = String.valueOf(FileUtils.getTimeStamp());
        File file = new File(path, phone + ".jpg");//第一个是父级文件路径，第二个是文件名
        if(!file.getParentFile().exists()){//判断父级路径是否存在
            file.mkdir();//创建文件夹
        }
        if(!file.exists()){//如果文件不存在满足条件
            try {
                file.createNewFile();//创建该文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String phoneStr = path + File.separator + phone + ".jpg";//组成一个图片的路径字符串
        //截取指定路径组成一个本地路径
        if(!filesCopy.equals("F:/workspace/MyJurisdiction/WebContent/images/587c589d26802.jpg")){//这是默认显示的图片路径
            filesCopy = request.getServletContext().getRealPath("/") + "photos" + filesCopy.substring(filesCopy.lastIndexOf("\\"),filesCopy.length());
        }
        FileUtils.fileCover(phoneStr, filesCopy);//将读取的流覆盖创建的图片
        user.setPhoto(phoneStr);//替换掉原本的路径
        userService.add(user);
        return "redirect:/user";
    }

    /**
     * 跳转修改
     * @return String
     */
    @RequestMapping(value="/userTable/{id}",method=RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map, Page<UserBo> page){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("1", "男");
        map1.put("2", "女");
        map1.put("3", "保密");
        map.put("sexs", map1);
        map.put("page", page);
        map.put("user", userService.getById(id));//根据id获取对象放入request中
        return "userAU";
    }

    /**
     * 保存修改
     * @return String
     */
    @RequestMapping(value="/userTable",method=RequestMethod.PUT)
    public String saveUpdate(User user, Page<UserBo> page, Map<String, Object> map){
        map.put("page", page);
        userService.update(user);
        return "userList";
    }

    /**
     * 删除用户
     * @return String
     */
    @RequestMapping(value="/userTable/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id){
        userService.delete(id);
    }

    /**
     * 根据用户id返回角色名集合
     * @param id
     * @return List<String>
     */
    @RequestMapping(value="/userTable/getRoles",method = RequestMethod.GET)
    @ResponseBody
    public List<String> getRoles(Integer id){
        return userService.getRoles(id);
    }

    /**
     * 根据用户id返回权限集合
     * @param id
     * @return List<Permission>
     */
    @RequestMapping(value="/userTable/getPermissions",method = RequestMethod.GET)
    @ResponseBody
        public List<Permission> getPermissions(Integer id){
        return userService.getPermissions(id);
    }

    /**
     * 给用户赋角色
     * @param id
     * @param roleIds
     */
    @RequestMapping(value="/userTable/setRoles",method = RequestMethod.GET)
    public void setRoles(Integer id,Integer[] roleIds){
        userService.setRoles(id,roleIds);
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
    public Map<String,Object> uploadFile(@RequestParam("files") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long startTime = FileUtils.getTimeStamp();//获得当前时间的时间戳
        String path = request.getServletContext().getRealPath("/") + "photos";//获取服务器路径         --这里要改成服务器的路径
        String fileName = file.getOriginalFilename();//获得文件名
        fileName = startTime + fileName.substring(fileName.lastIndexOf("."), fileName.length());//构建出一个唯一的文件名
        File filepath=new File(path,fileName);//构建成一个file对象
        //判断目标文件所在的目录是否存在
        if(!filepath.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            filepath.getParentFile().mkdirs();
        }
        //将内存中的数据写入磁盘
        file.transferTo(filepath);
        Map<String,Object> map = new HashMap<>();
        map.put("url", request.getServletContext().getContextPath() + File.separator  + "photos" + File.separator + fileName);
        return map;
    }

    /**
     * 从redis缓存中拿到用户的id返回头像的字节流到jsp
     * 注意：session中一般用来存储用户的信息，因为能很好的监控到用户的行为
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

}

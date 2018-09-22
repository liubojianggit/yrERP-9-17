package com.yr.user.controller;

import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;
import com.yr.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return "userAU";
    }

    /**
     * 保存添加
     * @return String
     */
    @RequestMapping(value="/userTable", method=RequestMethod.POST)
    public String saveAdd(User user){
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
}

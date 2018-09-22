package com.yr.common.controller;

import com.yr.common.service.MenuService;
import com.yr.common.service.impl.MenuServiceImpl;
import com.yr.entitys.bo.menuBO.MenuBO;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.menu.Menu;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    /**
     * 查询菜单表，返回json格式字符串
     * @return List<User>
     */
    @RequestMapping(value="/menuTable", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String query(){
        return menuService.query();
    }

    /**
     * 跳转添加页面
     * @return String
     */
    @RequestMapping(value="/menuTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map){
        map.put("menu", new Menu());//传入一个空的user对象
        return "menuAU";
    }

    /**
     * 保存添加
     * @return String
     */
    @RequestMapping(value="/menuTable", method=RequestMethod.POST)
    public String saveAdd(Menu menu){
        ((MenuServiceImpl)menuService).add(menu);
        return "redirect:/user";
    }

    /**
     * 跳转修改
     * @return String
     */
    @RequestMapping(value="/menuTable/{id}",method=RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map, Page<UserBo> page){
        Map<String, Object> map1 = new HashMap<>();
        map.put("page", page);
        map.put("user", ((MenuServiceImpl)menuService).getById(id));//根据id获取对象放入request中
        return "menuAU";
    }

    /**
     * 保存修改
     * @return String
     */
    @RequestMapping(value="/menuTable",method=RequestMethod.PUT)
    public String saveUpdate(Menu menu, Page<MenuBO> page, Map<String, Object> map){
        map.put("page", page);
        ((MenuServiceImpl)menuService).update(menu);
        return "userList";
    }

    /**
     * 删除用户
     * @return String
     */
    @RequestMapping(value="/menuTable/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id){
        ((MenuServiceImpl)menuService).delete(id);
    }

}

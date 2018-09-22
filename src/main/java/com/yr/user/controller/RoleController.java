package com.yr.user.controller;

import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Role;
import com.yr.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 操纵角色
 */
@Controller
@RequestMapping("u_role")
public class RoleController {
    @Autowired
    @Qualifier("roleServiceImpl")
    private RoleService roleService;


    /**
     * 如果检测到form表单提交带有id,直接将值存入request中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getAccount(@RequestParam(value="id",required=false) Integer id, Map<String, Object> map){
        if(id != null && id != 0){
            Role role = roleService.getById(id);
            map.put("Role", role);
        }
    }

    /**
     * 分页的形式查询role表的数据
     * @return List<RoleBo>
     */
    @RequestMapping(value="/roleTable", method = RequestMethod.GET)
    @ResponseBody
    public Page<RoleBo> query(Page<RoleBo> page){
        roleService.query(page);
        return page;
    }

    /**
     * 跳转添加页面
     * @return String
     */
    @RequestMapping(value="/roleTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map){
        map.put("role", new Role());//传入一个空的role对象
        return "roleAU";
    }

    /**
     * 保存添加
     * @return String
     */
    @RequestMapping(value="/roleTable", method=RequestMethod.POST)
    public String saveAdd(Role role){
        roleService.add(role);
        return "redirect:/role";
    }

    /**
     * 跳转修改
     * @return String
     */
    @RequestMapping(value="/roleTable/{id}",method=RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map, Page<RoleBo> page){
        map.put("page", page);
        map.put("role", roleService.getById(id));//根据id获取对象放入request中
        return "roleAU";
    }

    /**
     * 保存修改
     * @return String
     */
    @RequestMapping(value="/roleTable",method= RequestMethod.PUT)
    public String saveUpdate(Role role, Page<RoleBo> page, Map<String, Object> map){
        map.put("page", page);
        roleService.update(role);
        return "roleList";
    }

    /**
     * 删除角色
     * @return String
     */
    @RequestMapping(value="/roleTable/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id){
        roleService.delete(id);
    }

    /**
     * 给角色赋权限
     * @param id
     * @param roleIds
     */
    @RequestMapping(value="/roleTable/setPermissions",method = RequestMethod.GET)
    public void setRoles(Integer id,Integer[] roleIds){
        roleService.setPermissions(id,roleIds);
    }
}

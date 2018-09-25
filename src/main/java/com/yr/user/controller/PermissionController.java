package com.yr.user.controller;

import com.yr.entitys.bo.user.PermissionBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;
import com.yr.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 操纵权限
 */
@Controller
@RequestMapping("u_permission")
public class PermissionController {
    @Autowired
    @Qualifier("permissionServiceImpl")
    private PermissionService permissionService;

    /**
     * 如果检测到form表单提交带有id,直接将值存入request中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void getAccount(@RequestParam(value="id",required=false) Integer id, Map<String, Object> map){
        if(id != null && id != 0){
            Permission permission = permissionService.getById(id);
            map.put("permission", permission);
        }
    }

    /**
     * 跳转列表
     * @return
     */
    @RequestMapping(value = "/permissionTable",method = RequestMethod.GET)
    public String jumpList(){
        return "roleList";
    }

    /**
     * 分页的形式查询permission表的数据
     * @return List<PermissionBo>
     */
    @RequestMapping(value="/permissionTable/list", method = RequestMethod.GET)
    @ResponseBody
    public Page<PermissionBo> query(Page<PermissionBo> page){
        permissionService.query(page);
        return page;
    }

    /**
     * 跳转添加页面
     * @return String
     */
    @RequestMapping(value="/permissionTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map){
        map.put("permission", new Permission());//传入一个空的permission对象
        return "permissionAU";
    }

    /**
     * 保存添加
     * @return String
     */
    @RequestMapping(value="/permissionTable", method=RequestMethod.POST)
    public String saveAdd(Permission permission, HttpServletRequest request){
        permission.setCreateTime(new Date());//获取当前时间
        permission.setCreateEmp(((User)request.getSession().getAttribute("user")).getName());//获取当前用户名
        permissionService.add(permission);
        return "redirect:/permission";
    }

    /**
     * 跳转修改
     * @return String
     */
    @RequestMapping(value="/permissionTable/{id}",method=RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map, Page<PermissionBo> page){
        map.put("page", page);
        map.put("permission", permissionService.getById(id));//根据id获取对象放入request中
        return "permissionAU";
    }

    /**
     * 保存修改
     * @return String
     */
    @RequestMapping(value="/permissionTable",method= RequestMethod.PUT)
    public String saveUpdate(Permission permission, Page<PermissionBo> page, Map<String, Object> map, HttpServletRequest request){
        permission.setUpdateTime(new Date());//获取修改当前时间
        permission.setCreateEmp(((User)request.getSession().getAttribute("user")).getName());//获取修改用户
        map.put("page", page);
        permissionService.update(permission);
        return "permissionList";
    }

    /**
     * 删除权限
     * @return String
     */
    @RequestMapping(value="/permissionTable/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id){
        permissionService.delete(id);
    }

    /**
     * 回显角色所有的权限
     */
    @RequestMapping(value="/permissionTable/permissionList",method = RequestMethod.GET)
    @ResponseBody
    public List<PermissionBo> getPermission(Integer id){
        return permissionService.getPermission(id);
    }

    /**
     * 通过父级权限获得子级权限
     */
    @RequestMapping(value="/permissionTable/permission",method = RequestMethod.GET)
    public List<PermissionBo> getChildren(Integer rid, Integer pid){
        return permissionService.getchildren(rid, pid);
    }

    /**
     * 根据权限id获得所有父级权限
     */
    @RequestMapping(value="/permissionTable/getParent",method = RequestMethod.GET)
    public List<Permission> getParent(Integer id){
        return permissionService.getParent(id);
    }
}

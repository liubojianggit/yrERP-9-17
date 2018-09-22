package com.yr.department.controller;

import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("departmentTable")
public class DepartmentController {
    @Autowired
    private DepartmentService service;


    /**
     *分页查询部门列表数据
     * @param departbo 部门对象
     * @param currentPage 当前页
     * @param pageSize 当前页条数
     * @return
     */
    @RequestMapping(value="/")
    @ResponseBody
    public Page<Departmentbo> query(Departmentbo departbo , @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page<Departmentbo> page = new Page<>();
        page.setT(departbo);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        Page<Departmentbo> page1 = service.query(page);
        Map<String,Object> map=new HashMap<>();
        map.put("page", page1);
        return page1;
    }

    /**
     * 跳转添加页面无数据
     * @return
     * 单独查询部门的编号，以供父级id选择需要返回一个list
     */
    public String AddEcho(){
        List<Department> supcode=service.querycod();
        return "";

    }
    /**
     * 保存部门表添加的数据，前提添加数据不能为空
     * @param depart
     * @param map
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    public String add(Department depart, Map<String, Object> map){
        boolean isNull =service.isNullAdd(depart);
        if(isNull == false){
            map.put("depart",depart);
            map.put("uperror", 1);//如果修改的值为空就不修改并且跳转到修改页面(重新刷新页面)
            return "departadd";
        }else{
            service.add(depart);
            return "departList";
        }
    }
    /**
     * 根据id删除部门表
     * @return 返回分页查询页面
     */

    @RequestMapping(value = "//{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "departList";
    }
    /**
     * 根据id回显部门修改的数据
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/update/{id}")
    public String upEcho(@PathVariable Integer id,Map<String, Object> map,Departmentbo departbo,Page<Departmentbo>page) {
        page.setT(departbo);
        Department departs = service.getById(id);
        map.put("depart",departs);
        map.put("page", page);
        return "add";
    }
    /**9
     * 根据id回显后的值修改用户
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value="/account",method = RequestMethod.PUT)
    public String update(Department depart
            ,@RequestParam("id")Integer id,Map<String, Object> map,Departmentbo departbo,Page<Departmentbo>page){
        boolean isNull =service.isNullUpdate(depart);
        if(isNull == false ){
            Department depart1 = service.getById(id);
            map.put("depart", depart1);
            map.put("uperror",2);
            return "Aadd";
        }else{
            page.setT(departbo);
            map.put("page", page);
            service.update(depart);
            return "AList";
        }
    }
}

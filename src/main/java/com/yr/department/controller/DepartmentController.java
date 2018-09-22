package com.yr.department.controller;

import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/department")
public class DepartmentController {
    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentService departmentService;


    /**
     *分页查询部门列表数据
     * @param departbo 部门对象
     * @param currentPage 当前页
     * @param pageSize 当前页条数
     * @return
     */
    @RequestMapping(value="/departmentTable",method = RequestMethod.GET)
    @ResponseBody
    public Page<Departmentbo> query(Departmentbo departbo , @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page<Departmentbo> page = new Page<>();
        page.setT(departbo);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        Page<Departmentbo> page1 = departmentService.query(page);
        Map<String,Object> map=new HashMap<>();
        map.put("page", page1);
        return page1;
    }

    /**
     * 跳转添加页面无数据
     * @return
     * 单独查询部门的编号，以供父级id选择需要返回一个list
     */
    @RequestMapping(value = "/departmentTable/add",method = RequestMethod.GET)
    public String AddEcho(){
        List<Department> supcode=departmentService.querycod();
        return "";
    }

    /**
     * 保存部门表添加的数据，前提添加数据不能为空
     * @param depart
     * @param map
     * @return
     */
    @RequestMapping(value="/departmentTable",method = RequestMethod.POST)
    public String add(Department depart, Map<String, Object> map){
        boolean isNull =departmentService.isNullAdd(depart);
        if(isNull == false){
            map.put("depart",depart);
            map.put("uperror", 1);//如果修改的值为空就不修改并且跳转到修改页面(重新刷新页面)
            return "departadd";
        }else{
            departmentService.add(depart);
            return "departList";
        }
    }

    /**
     * 根据id删除部门表
     * @return 返回分页查询页面
     */
    @RequestMapping(value = "/departmentTable/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        departmentService.delete(id);
        return "departList";
    }

    /**
     * 根据id回显部门修改的数据
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/departmentTable/{id}",method = RequestMethod.GET)
    public String upEcho(@PathVariable Integer id,Map<String, Object> map,Departmentbo departbo,Page<Departmentbo>page) {
        page.setT(departbo);
        Department departs = departmentService.getById(id);
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
    @RequestMapping(value="/departmentTable",method = RequestMethod.PUT)
    public String update(Department depart
            ,@RequestParam("id")Integer id,Map<String, Object> map,Departmentbo departbo,Page<Departmentbo>page){
        boolean isNull =departmentService.isNullUpdate(depart);
        if(isNull == false ){
            Department depart1 = departmentService.getById(id);
            map.put("depart", depart1);
            map.put("uperror",2);
            return "Aadd";
        }else{
            page.setT(departbo);
            map.put("page", page);
            departmentService.update(depart);
            return "AList";
        }
    }
}

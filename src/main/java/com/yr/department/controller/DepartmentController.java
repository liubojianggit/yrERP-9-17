package com.yr.department.controller;

import java.util.HashMap;
import java.util.Map;

import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 部门表Controller接口
 */
@RequestMapping(value = "/department")
@Controller
public class DepartmentController {
    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentService departmentService;//把epartmentService对象传过来

    //如果表单中带有ID 就执行这里
    //@RequestParam(value="id", required = false)表示执行该方法时不必要有id这个参数值，如果是ture则是必须要有id这个参数
    @ModelAttribute
    public void getAccount(@RequestParam (value="id" ,required=false) Integer id , Map<String ,Object> map){
        if(id !=0 &&id !=null){
            Department department=departmentService.departmentId(id);
            map.put("department", department);
        }
    }

    /**
     * 放回固定格式的字符串，生成菜单树形表
     * @return
     */
    @RequestMapping(value="/dapartmentTable/list",method=RequestMethod.GET,produces="application/json;charset=UTF-8")//防止ajxa页面出现乱码
    public String query(){
        departmentService.query();
        return query();
    }

    /**
     * 跳转操作页面
     * @return
     */
    @RequestMapping(value = "/departmentTable",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String List(){

        return "redirect:/departmentList";
    }
    /**
     * 跳转添加页面
     * @returns
     */
    @RequestMapping(value="/departmentTable/add",method=RequestMethod.GET)
    public String add(Map<String , Object>map){
        map.put("department", new Department());//传入一个空的对象
        return "redirect:/departmentAU";//返回新增 修改页面
    }
    /**
     * 保存添加
     */
    @RequestMapping(value="/departmentTable",method=RequestMethod.POST)
    public String adds(Department department){
        departmentService.add(department);//调用添加方法
        return "redirect:/departmentList";//返回部门页面
    }
    /**
     * 根据ID 删除部门
     * @return
     */
    @RequestMapping(value="/departmentTable/{id}",method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id){
        departmentService.delete(id);//调用删除方法
        return "redirect:/departmentList";//返回部门页面
    }
    /**
     * 跳转修改 部门
     */
    @RequestMapping(value="/departmentTable/{id}",method=RequestMethod.GET)
    public String update(@PathVariable("id") Integer id){
        Map<String,Object> map=new HashMap<>();
        map.put("department", departmentService.departmentId(id));//调用查询ID方法回显数据
        return "redirect:/departmentAU";//返回新增 修改页面
    }

    /**
     * 保存修改 部门
     */
    @RequestMapping(value="/departmentTable",method=RequestMethod.PUT)
    public String updates(Department department){
        departmentService.update(department);
        return "redirect:/departmentList";//返回部门页面
    }
}

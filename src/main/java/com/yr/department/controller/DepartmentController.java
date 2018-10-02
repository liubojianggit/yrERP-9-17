package com.yr.department.controller;

import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        if(!"".equals(id) && id !=null){
            Departmentbo departmentbo = departmentService.departmentId(id);
            map.put("departmentbo", departmentbo);
        }
    }

    /**
     * 放回固定格式的字符串，生成菜单树形表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/departmentTable/list",method=RequestMethod.GET,produces="application/json;charset=UTF-8")//防止ajxa页面出现乱码
    public String query(Departmentbo departmentbo,Page<Departmentbo> page){
        page.setT(departmentbo);
        return departmentService.queryDepartmentbo(page);
    }

    /**
     * 跳转操作页面
     * @return
     */
    @RequestMapping(value = "/departmentTable",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String List(){

        return "departmentListPage";
    }
    /**
     * 跳转添加页面
     * @returns
     */
    @RequestMapping(value="/departmentTable/add",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String add(Map<String , Object>map){
        map.put("departmentbo", new Departmentbo());//传入一个空的对象
        map.put("supDepartment",departmentService.getDepartmentList());
        return "departmentAU";//返回新增 修改页面
    }
    /**
     * 保存添加
     */
    @ResponseBody
    @RequestMapping(value="/departmentTable",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    public String add(Departmentbo departmentbo){
        departmentService.add(departmentbo);//调用添加方法
        return "{\"code\":1,\"msg\":\"新增保存成功\"}";
    }
    /**
     * 根据ID 删除部门
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/departmentTable/{id}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public String delete(@PathVariable("id") Integer[] id){
        departmentService.delete(id);//调用删除方法
        return "{\"code\":1,\"msg\":\"删除成功\"}";
    }
    /**
     * 跳转修改 部门
     */
    @RequestMapping(value="/departmentTable/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String update(@PathVariable("id") Integer id,Map<String , Object>map){
        map.put("departmentbo", departmentService.departmentId(id));//调用查询ID方法回显数据
        map.put("supDepartment",departmentService.getDepartmentList());
        return "departmentAU";//返回新增 修改页面
    }

    /**
     * 保存修改 部门
     */
    @ResponseBody
    @RequestMapping(value="/departmentTable",method=RequestMethod.PUT,produces="application/json;charset=UTF-8")
    public String updates(Departmentbo departmentbo){
        departmentService.update(departmentbo);
        return "{\"code\":1,\"msg\":\"修改保存成功\"}";
    }
}
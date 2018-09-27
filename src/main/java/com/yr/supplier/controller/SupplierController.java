package com.yr.supplier.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;
import com.yr.entitys.user.User;
import com.yr.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 供应商表的controller
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;

    /**
     * 调用这个类的所有方法前都要执行有@ModelAttribute的方法
     @param id
      * @param map
     * @throws SQLException
     */
    @ModelAttribute
    public void query(@RequestParam(value ="id",required=false) Integer id,Map<String, Object> map) throws SQLException{// 绑定站位符
        if (id != null&&id !=0) {
            SupplierBo supplierBO=service.getById(id);
            map.put("supplierBo",supplierBO);
        }
    }
    /**
     * 跳转到拥有供应商的查询列表，没有数据操作
     * @return
     */
    @RequestMapping(value = "/supplierTable",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String list(){
        System.out.println("aa");
        return "supplierList";
    }
    /**
     *分页查询供应商列表数据
     * @param supplierBo 供应商对象
     * @return
     */
    @RequestMapping(value="/supplierTable/list",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String query(SupplierBo supplierBo,Page<SupplierBo>page){
       page.setT(supplierBo);
       String json = service.query(page);
        return json;
    }
    /**
     * 没业务据操作，只跳转到供应商添加页面
     * @return
     */
    @RequestMapping(value="/supplierTable/add",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String AddEcho(Map<String , Object>map){
        map.put("supplierBo",new SupplierBo());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("一级","一级");
        map1.put("二级","二级");
        map1.put("三级","三级");
        map.put("rankList",map1);
        return "supplierAU";
    }
    /**
     * 保存供应商添加的数据，前提添加数据不能为空
     * @param supplierBo
     * @return
     */
    @RequestMapping(value="/supplierTable",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String add(SupplierBo supplierBo,HttpServletRequest request) {
       /* boolean isTell=service.isTell(supplierBo.getSupplier().getPhoneNumber());
        if(isTell){
            return "{\"code\":2,\"msg\":\"电话格式错误\"}";
        }*/
       supplierBo.getSupplier().setCreateTime(new Date());
       supplierBo.getSupplier().setCreateEmp("吕");
       //supplierBo.getSupplier().setCreateEmp(((User)request.getSession().getAttribute("user")).getName());
        service.add(supplierBo);
            return "{\"code\":1,\"msg\":\"保存成功\"}";
    }

    /**
     * 根据id删除供应商表数据
     * @return 返回分页查询页面
     */
    @ResponseBody
    @RequestMapping(value = "/supplierTable/{id}", method = RequestMethod.DELETE,produces="application/json;charset=UTF-8")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "{\"code\":1,\"msg\":\"删除成功\"}";
    }

    /**
     * 根据id回显修改供应商数据
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/supplierTable/{id}",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String upEcho(@PathVariable Integer id,Map<String, Object> map) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("一级","一级");
        map1.put("二级","二级");
        map1.put("三级","三级");
        map.put("rankList",map1);
        map.put("supplierBo",service.getById(id));
        return "supplierAU";
    }
    /**
     * 根据id回显后的值修供应商库数据
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value="/supplierTable",method = RequestMethod.PUT,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String update(Map<String, Object> map,SupplierBo supplierBo){
        /* 获取String类型的时间 */
        supplierBo.getSupplier().setUpdateTime(new Date());
        supplierBo.getSupplier().setUpdateEmp("吕");
        //supplierBo.getSupplier().setUpdateEmp(((User)request.getSession().getAttribute("user")).getName());
            service.update(supplierBo);
            return "{\"code\":1,\"msg\":\"修改成功\"}";

    }

}

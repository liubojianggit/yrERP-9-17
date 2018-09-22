package com.yr.supplier.controller;

import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;
import com.yr.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("supplierTable")
public class SupplierController {
    @Autowired
    private SupplierService service;
    /**
     *分页查询工会隐身列表数据
     * @param supplierBo 供应商对象
     * @param currentPage 当前页
     * @param pageSize 当前页条数
     * @return
     */
    @RequestMapping(value="/Apage")
    @ResponseBody
    public Page<SupplierBo> query(SupplierBo supplierBo , @RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize){
        Page<SupplierBo> page = new Page<>();
        page.setT(supplierBo);
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        Page<SupplierBo> page1 = service.query(page);
        Map<String,Object> map=new HashMap<>();
        map.put("page", page1);
        return page1;
    }
    /**
     * 没业务据操作，只跳转到供应商添加页面
     * @return
     */
    @RequestMapping(value="/")
    public String AddEcho(){

        return "supplieradd";//添加页面的jsp前缀
    }
    /**
     * 保存供应商添加的数据，前提添加数据不能为空
     * @param supplier
     * @param map
     * @return
     */
    @RequestMapping(value="",method = RequestMethod.POST)
    public String add(Supplier supplier, Map<String, Object> map) {
        boolean isNull = service.isNullAdd(supplier);
        if(isNull == false){
            map.put("depot",supplier);
            map.put("uperror", 1);
            return "supplieradd";
        }else{
            service.add(supplier);
            return "supplierList";
         }
    }
    /**
     * 根据id删除供应商表数据
     * @return 返回分页查询页面
     */

    @RequestMapping(value = "//{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "AList";
    }

    /**
     * 根据id回显修改供应商数据
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/UpdateEcho/{id}")
    public String upEcho(@PathVariable Integer id,Map<String, Object> map,SupplierBo supplierBo,Page<SupplierBo>page) {
        page.setT(supplierBo);
        Supplier depots = service.getById(id);
        map.put("supplier",depots);
        map.put("page", page);
        return "supplieradd";
    }
    /**
     * 根据id回显后的值修供应商库数据
     * @param
     * @param map
     * @return
     */
    @RequestMapping(value="/account",method = RequestMethod.PUT)
    public String update(Supplier supplier
            ,@RequestParam("id")Integer id,Map<String, Object> map,SupplierBo supplierBo,Page<SupplierBo>page){
        boolean isNull =service.isNullUpdate(supplier);
        if(isNull == false ){
            Supplier supplier1 = service.getById(id);
            map.put("supplier", supplier1);
            map.put("uperror",2);//修改数据为空返回一个值2，
            return "supplierAadd";
        }else{
            page.setT(supplierBo);
            map.put("page", page);
            service.update(supplier);
            return "supplierList";
        }
    }

}

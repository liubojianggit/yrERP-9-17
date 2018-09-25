package com.yr.supplier.controller;

import com.yr.core.redis.JedisManager;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;
import com.yr.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 跳转到拥有供应商的查询列表，没有数据操作
     * @return
     */
    @RequestMapping(value = "/supplierTable/List",method = RequestMethod.GET)
    public String list(){
        System.out.println("aa");
        return "supplierList";
    }
    /**
     *分页查询供应商列表数据
     * @param supplierBo 供应商对象
     * @param currentPage 当前页
     * @param pageSize 当前页条数
     * @return
     */
    @RequestMapping(value="/supplierTable",method = RequestMethod.GET)
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
    @RequestMapping(value="/supplierTable/add",method = RequestMethod.GET)
    public String AddEcho(){

        return "supplierAdd";//添加页面的jsp前缀
    }
    /**
     * 保存供应商添加的数据，前提添加数据不能为空
     * @param supplier
     * @param map
     * @return
     */
    @RequestMapping(value="/supplierTable",method = RequestMethod.POST)
    public String add(Supplier supplier, Map<String, Object> map) {
        boolean isNull = service.isNullAdd(supplier);
        boolean isTell=service.isTell(supplier.getPhoneNumber());
        if(isNull == false){
            map.put("depot",supplier);
            map.put("uperror", 1);
            return "supplieradd";
        }if(isTell){
            map.put("tell",2);//电话错误
            return "supplieradd";
        }
            service.add(supplier);
            return "supplierList";
    }
    /**
     * 根据id删除供应商表数据
     * @return 返回分页查询页面
     */

    @RequestMapping(value = "/supplierTable/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "supplierList";
    }

    /**
     * 根据id回显修改供应商数据
     * @param id
     * @param map 放入map中存放request，方便页面拿取
     * @return
     */
    @RequestMapping(value = "/supplierTable/{id}",method = RequestMethod.GET)
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
    @RequestMapping(value="/supplierTable",method = RequestMethod.PUT)
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

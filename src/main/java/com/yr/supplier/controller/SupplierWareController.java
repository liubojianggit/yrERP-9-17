package com.yr.supplier.controller;

import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.supplierWares;
import com.yr.supplier.service.SupplierWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *供应商品的controller层，用于后台代码和前台页面交互
 */
@Controller
@RequestMapping(value = "supp_wares")
public class SupplierWareController {
    @Autowired
    private SupplierWareService sws;
    @ModelAttribute
    public void Pojo (@RequestParam(value="id",required = false)Integer id, Map<String,Object> map){
        if (id!=null){
            map.put("supplierWare",sws.getSupplierWare(id));
        }
    }

    /**
     * 用于跳转数据查询页面
     * @return
     */
    @RequestMapping(value = "supplierTable/list",method = RequestMethod.GET)
    public String getListPage(){
        return "";
    }
    /**
     * 用于跳转到添加页面和修改页面
     * @return
     */
    @RequestMapping(value = "supplierTable/addAndUpdate",method = RequestMethod.GET)
    public String getAddPage(){
        return "";
    }

    /**
     * 供应商品添加方法，用于前台添加数据
     * @param supplierWare
     * @return
     */
    @RequestMapping(value = "supplierTable",method =RequestMethod.POST)
    public String addWare(@ModelAttribute("supplierWare")supplierWares supplierWare){
        sws.add(supplierWare);
        return "";
    }

    /**
     * 根据id来删除供应商品
     * @param id
     * @return
     */
    @RequestMapping(value = "supplierTable",method = RequestMethod.DELETE)
    public String deleteWare(@PathVariable Integer id){
        sws.delete(id);
        return "";
    }

    /**
     * 根据id来修改供应商品的信息
     * @param supplierWare
     * @param map
     * @return
     */
    @RequestMapping(value = "supplierTable",method = RequestMethod.PUT)
    public String updateWare(@ModelAttribute("supplierWare") supplierWares supplierWare, Map<String,Object>map){
        sws.update(supplierWare);
        return"";
    }
    /**
     * 供应商品查询方法，前台可以通过这个方法进行数据的查询
     * @param supplierWare
     * @param map
     * @return 查询数据
     */
    @RequestMapping(value = "supplierTable",method = RequestMethod.GET)
    @ResponseBody
    public Page<SupplierWareBo> queryWare(Page<SupplierWareBo> supplierWare, Map<String,Object>map){
        supplierWare = sws.query(supplierWare);
        map.put("ware",supplierWare);
        return supplierWare;
    }
}

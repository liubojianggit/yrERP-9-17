package com.yr.order.controller;

import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Sale;
import com.yr.order.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sale_order")
public class SaleController {//销售订单Controller
    @Autowired
    private SaleService saleService;

    /**
     * 如果检测到form表单提交代有id，直接将值存入request中
     * @param id
     * @param map
     */
    @ModelAttribute
    public void ModelAttribute(@RequestParam(value = "id",required = false)Integer id, Map<String,Object>map){
        if (id != null && id != 0){
            Sale sale = saleService.getById(id);
            map.put("sale",sale);
        }
    }

    /**
     * 销售订单表页面查询接口
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/list",method = RequestMethod.GET)
    public String index(){
        return "";
    }

    /**
     * 分页的形式查询销售订单表的数据
     * @param saleBO
     * @param page
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.GET)
    @ResponseBody
    public Page<SaleBO>query(SaleBO saleBO, Page<SaleBO>page){
        page.setT(saleBO);
        saleService.query(page);
        return page;
    }

    /**
     * 跳转添加销售订单表页面
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/add",method = RequestMethod.GET)
    public String jumpAdd(Map<String,Object>map){
        map.put("sale",new Sale());//传入一个空的user对象
        Map<String,Object> map1=new HashMap<>();
        map1.put("0","退货");
        map1.put("1","交易成功");
        map.put("sates",map1);
        map.put("sale", new Sale());
        return "saleAU";
    }

    /**
     * 保存添加销售订单表
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.POST)
    public String saveAdd(Sale sale){
        saleService.add(sale);
        return "redirect:/sale";
    }

    /**
     * 跳转修改销售订单表页面
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/{id}",method = RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map, SaleBO saleBO, Page<SaleBO> page){
        page.setT(saleBO);
        Map<String,Object>map1 = new HashMap<>();
        map1.put("0","退货");
        map1.put("1","交易成功");
        map.put("states",map);
        map.put("page",page);
        map.put("sale",saleService.getById(id));//根据id获取对象放入request中
        return "saleAU";
    }

    /**
     * 保存修改销售订单表
     * @param sale
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.PUT)
    public String SaveOrUpdate(Sale sale, SaleBO saleBO, Page<SaleBO> page, Map<String, Object> map){
        page.setT(saleBO);
        map.put("page",page);
        saleService.update(sale);
        return "saleList";
    }

    /**
     * 根据id删除销售订单表
     * @param id
     */
    @RequestMapping(value = "/sale_orderTable/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable Integer id){
        saleService.delete(id);
    }
}

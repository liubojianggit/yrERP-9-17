package com.yr.order.controller;


import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.order.SaleOrder;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.User;
import com.yr.order.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.regex.Pattern;

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
            SaleOrder saleOrder = saleService.getById(id);
            map.put("saleOrder",saleOrder);
        }
    }

    /**
     * 销售订单表页面查询接口
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public String index(){
        return "saleList";
    }

    /**
     * 分页的形式查询销售订单表的数据
     * @param saleBO
     * @param page
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/list",method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String query(SaleBO saleBO, Page<SaleBO>page){
        page.setT(saleBO);//将bo类置入对象
        String json = saleService.query(page);//将list返回一个json字符串
        return json;

    }
    /**
     * 跳转添加销售订单表页面
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/add",method = RequestMethod.GET)
    public String jumpAdd(Map<String,Object>map){
        map.put("sale",new SaleOrder());//传入一个空的sale对象
        Map<String,Object> map1=new HashMap<>();
        map1.put("0","退货");
        map1.put("1","交易成功");
        map.put("states",map1);
        map.put("sale", new SaleOrder());
        return "saleAU";
    }

    /**
     * 保存添加销售订单表
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String saveAdd(SaleOrder sale, HttpServletRequest request){
        sale.setCreateTime(new Date());//获取当前时间
        sale.setCreateEmp(((User)request.getSession().getAttribute("user")).getName());//获取当前用户名
        saleService.add(sale);
        return "{\"code\":1,\"msg\":\"保存成功\"}";
    }

    /**
     * 跳转修改销售订单表页面
     * @return
     */
    @RequestMapping(value = "/sale_orderTable/{id}",method = RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map){
        Map<String,Object>map1 = new HashMap<>();
        map1.put("0","退货");
        map1.put("1","交易成功");
        map.put("states",map1);
        map.put("sale",saleService.getById(id));//根据id获取对象放入request中
        return "saleAU";
    }

    /**
     * 保存修改销售订单表
     * @param sale
     * @return
     */
    @RequestMapping(value = "/sale_orderTable",method = RequestMethod.PUT, produces="application/json;charset=UTF-8")
    public String SaveOrUpdate(SaleOrder sale, HttpServletRequest request){
        sale.setUpdateTime(new Date());//获取修改当前时间
        sale.setUpdateEmp(((SaleOrder)request.getSession().getAttribute("sale")).getApprover());//获取修改人（审核人）
        saleService.update(sale);
        return "{\"code\":1,\"msg\":\"修改成功\"}";
    }

    /**
     * 根据id删除销售订单表
     * @param id
     */
    @RequestMapping(value = "/sale_orderTable/delete/{id}",method = RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String delete(@PathVariable Integer id){
        saleService.delete(id);
        return "{\"code\":1,\"msg\":\"删除成功\"}";
    }
}

package com.yr.order.controller;

import com.yr.entitys.bo.orderBO.RequisitionBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Requisition;
import com.yr.order.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 采购列表增删改查接口
 */
@Controller
@RequestMapping(value = "requisition")
public class RequisitionController {
    @Autowired
    private RequisitionService requisitionServiceImpl;

    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null && id != 0) {
            Requisition requisition = requisitionServiceImpl.getRequisitionById(id);
            map.put("requisition", requisition);
        }
    }

    /**
     * 数据查询接口
     * 跳转查询页面
     * @return
     */
    @RequestMapping(value = "/requisitionTable/list", method = RequestMethod.GET)
    public String index() {
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.GET)
    public Page<Requisition> query(RequisitionBO requisitionBO, Page<RequisitionBO> page) {
        page.setT(requisitionBO);
        Page<Requisition> page1 = requisitionServiceImpl.query(page);
        return page1;

    }

    /**
     * 添加数据接口；
     * 跳转添加页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/requisitionTable/add", method = RequestMethod.GET)
    public String jumpAdd(Map<String, Object> map) {
        Map<Integer,String> status = new HashMap<Integer, String>();
        status.put(0,"驳回");
        status.put(1,"交易成功");
        status.put(2,"待审核");
        status.put(3,"申请退货");
        status.put(4,"退货成功");
        //将状态设进map
        map.put("status",status);
        map.put("requisition", new Requisition());
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.POST)
    public String add(Requisition requisition ,HttpServletRequest request) {
        //将时间戳设置进入创建时间
        requisition.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //将修改时间设进修改时间，初始修改时间，后期会改
        requisition.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取session 当中当前登录用户，session属性名从login登录的传过来，
        requisition.setCreateEmp((String) request.getSession().getAttribute(""));
        //这个初始的修改人，后期会改
        requisition.setUpdateEmp((String) request.getSession().getAttribute(""));

        //获取采购单价和采购数量，计算采购商品总价格，并把它设入setTotalPrice();
        double total = requisition.getUnitPrice()*requisition.getPurchaseNumber();
        requisition.setTotalPrice(total);
        requisitionServiceImpl.add(requisition);
        return null;
    }
    /**
     * 修改接口；
     *  跳转修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/requisitionTable/update/{id}", method = RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map) {
        Requisition requisition = requisitionServiceImpl.getRequisitionById(id);
        Map<Integer,String> status = new HashMap<Integer, String>();
        status.put(0,"驳回");
        status.put(1,"交易成功");
        status.put(2,"待审核");
        status.put(3,"申请退货");
        status.put(4,"退货成功");
        //将状态设进map
        map.put("status",status);
        //将修改后的采购订单对象设进map
        map.put("requisition", requisition);
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.PUT)
    public String update(@ModelAttribute("requisition") Requisition requisition,HttpServletRequest request) {
        //获取当前登录用户并把它设为修改人
        requisition.setUpdateEmp((String)request.getSession().getAttribute(""));
        //获取当前时间为数据修改时间；
        requisition.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取采购单价和采购数量，计算采购商品总价格，并把它设入setTotalPrice();
        double total = requisition.getUnitPrice()*requisition.getPurchaseNumber();
        requisition.setTotalPrice(total);

        requisitionServiceImpl.update(requisition);
        return null;
    }

    /**
     * 这个是ajax 请求不要跳转，删除接口
     * 删除接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/requisitionTable/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        requisitionServiceImpl.delete(id);
        return null;
    }
}

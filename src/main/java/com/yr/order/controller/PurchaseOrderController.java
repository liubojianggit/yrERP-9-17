package com.yr.order.controller;

import com.yr.entitys.bo.orderBO.purchaseOrderBO;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.page.Page;
import com.yr.order.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个是一个采购的controller层
 */
@Controller
@RequestMapping(value = "requisition")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderServiceImpl;

    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null && id != 0) {
            PurchaseOrder purchaseOrder = purchaseOrderServiceImpl.getRequisitionById(id);
            map.put("purchaseOrder", purchaseOrder);
        }
    }

    /**
     * 数据查询接口
     * 跳转查询页面
     * @return
     */
    @RequestMapping(value = "requisitionTable",method = RequestMethod.GET)
    public String index() {
        return null;
    }

    /**
     * 分页查询采购表数据，并且返回json 数据；
     * @param requisitionBO
     * @param page
     * @return json
     */
    @RequestMapping(value = "/requisitionTable/list", method = RequestMethod.GET)
    @ResponseBody
    public String query(purchaseOrderBO requisitionBO, Page<purchaseOrderBO> page) {
        page.setT(requisitionBO);
        String json  = purchaseOrderServiceImpl.query(page);
        return json;
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
        map.put("purchaseOrder", new PurchaseOrder());
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.POST)
    public String add(PurchaseOrder purchaseOrder ,HttpServletRequest request) {
        //将时间戳设置进入创建时间
        purchaseOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //将修改时间设进修改时间，初始修改时间，后期会改
        purchaseOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取session 当中当前登录用户，session属性名从login登录的传过来，
        purchaseOrder.setCreateEmp((String) request.getSession().getAttribute(""));
        //这个初始的修改人，后期会改
        purchaseOrder.setUpdateEmp((String) request.getSession().getAttribute(""));

        //获取采购单价和采购数量，计算采购商品总价格，并把它设入setTotalPrice();
        double total = purchaseOrder.getUnitPrice()*purchaseOrder.getPurchaseNumber();
        purchaseOrder.setTotalPrice(total);
        purchaseOrderServiceImpl.add(purchaseOrder);

        return "{\"code\":1,\"msg\":\"新增保存成功\"}";
    }
    /**
     * 修改接口；
     *  跳转修改页面
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/requisitionTable/{id}", method = RequestMethod.GET)
    public String jumpUpdate(@PathVariable Integer id, Map<String, Object> map) {
        PurchaseOrder purchaseOrder = purchaseOrderServiceImpl.getRequisitionById(id);
        Map<Integer,String> status = new HashMap<Integer, String>();
        status.put(0,"驳回");
        status.put(1,"交易成功");
        status.put(2,"待审核");
        status.put(3,"申请退货");
        status.put(4,"退货成功");
        //将状态设进map
        map.put("status",status);
        //将修改后的采购订单对象设进map
        map.put("purchaseOrder", purchaseOrder);
        return null;
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.PUT)
    public String update(@ModelAttribute("requisition") PurchaseOrder purchaseOrder,HttpServletRequest request) {
        //获取当前登录用户并把它设为修改人
        purchaseOrder.setUpdateEmp((String)request.getSession().getAttribute(""));
        //获取当前时间为数据修改时间；
        purchaseOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取采购单价和采购数量，计算采购商品总价格，并把它设入setTotalPrice();
        double total = purchaseOrder.getUnitPrice()*purchaseOrder.getPurchaseNumber();
        purchaseOrder.setTotalPrice(total);

        purchaseOrderServiceImpl.update(purchaseOrder);
        return "{\"code\":1,\"msg\":\"修改成功\"}";
    }

    /**
     * 这个是ajax 请求不要跳转，删除接口
     * 删除接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/requisitionTable/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        purchaseOrderServiceImpl.delete(id);
        return "{\"code\":1,\"msg\":\"删除成功\"}";
    }
}

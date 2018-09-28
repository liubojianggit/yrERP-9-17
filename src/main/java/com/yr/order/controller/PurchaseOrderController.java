package com.yr.order.controller;

import com.yr.department.service.DepartmentService;
import com.yr.depot.service.DepotService;
import com.yr.entitys.bo.orderBO.PurchaseOrderBo;
import com.yr.entitys.bo.orderBO.RandomUtil;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.page.Page;
import com.yr.order.service.PurchaseOrderService;
import com.yr.supplier.service.SupplierService;
import com.yr.supplier.service.SupplierWareService;
import com.yr.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 这个是一个采购的controller层
 */
@Controller
@RequestMapping(value = "requisition")
@SessionAttributes(value = {"username"}, types = {Integer.class})//这里指定一下 Session 才能获得指定的数据
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderServiceImpl;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userServices;//人员

    @Qualifier("departmentServiceImpl")
    @Autowired
    private DepartmentService departmentServices;//部门

    @Autowired
    @Qualifier("supplierWareServiceImpl")
    private SupplierWareService supplierWareServices;//供应商品

    @Autowired
    @Qualifier("depotServiceImpl")
    private DepotService depotServices;//仓库

    @Autowired
    @Qualifier("supplierServiceImpl")
    private SupplierService supplierServices;//供应商



    @ModelAttribute
    public void modelAttribute(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null && id != 0) {
            PurchaseOrder purchaseOrder = purchaseOrderServiceImpl.getRequisitionById(id);
            //返回采购实体
            map.put("purchaseOrder", purchaseOrder);
            /*//页面返回实体BO类
            map.put("purchaseOrderBO",new PurchaseOrderBo(purchaseOrder));*/
        }
    }

    /**
     * 数据查询接口
     * 跳转查询页面
     * @return
     */
    @RequestMapping(value = "requisitionTable",method = RequestMethod.GET)
    public String index() {
        return "purchaseOrderList";
    }

    /**
     * 分页查询采购表数据，并且返回json 数据；
     * @param requisitionBO
     * @param page
     * @return json
     *
     *
     */
    @RequestMapping(value = "/requisitionTable/list", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query(PurchaseOrderBo requisitionBO, Page<PurchaseOrderBo> page) {
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
        //获取部门对象集合
        Map<String,Object> departmentList =  departmentServices.querys();
        //获取仓库对象集合0 = {HashMap$Node@7834} "1002" -> "大浪仓库"
        Map<String,Object> depotList =  depotServices.queryDepots();
        //获取供应商对象集合
        Map<String,Object> supplierList = supplierServices.querySuppliers();

        map.put("departmentList",departmentList);
        map.put("depotList",depotList);
        map.put("supplierList",supplierList);
        map.put("purchaseOrder", new PurchaseOrder());

        return "purchaseOrderAU";
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.POST)
    public String add(PurchaseOrder purchaseOrder ,HttpServletRequest request) {
        //将时间戳设置进入创建时间
        purchaseOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
        //将修改时间设进修改时间，初始修改时间，后期会改
        purchaseOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        //获取session 当中当前登录用户，session属性名从login登录的传过来，
        purchaseOrder.setCreateEmp((String) request.getSession().getAttribute("username"));
        //这个初始的修改人，后期会改
        purchaseOrder.setUpdateEmp((String) request.getSession().getAttribute("username"));

        //获取采购单价和采购数量，计算采购商品总价格，并把它设入setTotalPrice();
        double total = purchaseOrder.getUnitPrice()*purchaseOrder.getPurchaseNumber();
        purchaseOrder.setTotalPrice(total);

        //随机数生成订单编号；
        String code = RandomUtil.generateUpperString(13);
        purchaseOrder.setCode(code);

        //所有订单申请表单初始化都是-->>待审核状态2
        // (0驳回，1交易成功，2待审核，3申请退货，4退货成功)
        purchaseOrder.setStatus(2);
        //修改人即使修改人；
        purchaseOrder.setApprover((String) request.getSession().getAttribute("username"));
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
        //将修改后的采购订单对象设进map
        map.put("purchaseOrder", purchaseOrder);
        return "purchaseOrderAU";
    }

    @RequestMapping(value = "/requisitionTable", method = RequestMethod.PUT)
    public String update(@ModelAttribute("requisition") PurchaseOrder purchaseOrder,HttpServletRequest request) {
        //获取当前登录用户并把它设为修改人
        purchaseOrder.setUpdateEmp((String)request.getSession().getAttribute("username"));
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

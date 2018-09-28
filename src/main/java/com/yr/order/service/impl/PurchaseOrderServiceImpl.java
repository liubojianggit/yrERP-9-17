package com.yr.order.service.impl;

import com.yr.department.service.DepartmentService;
import com.yr.depot.service.DepotService;
import com.yr.entitys.bo.orderBO.PurchaseOrderBo;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.page.Page;
import com.yr.order.dao.PurchaseOrderDao;
import com.yr.order.service.PurchaseOrderService;
import com.yr.supplier.service.SupplierService;
import com.yr.supplier.service.SupplierWareService;
import com.yr.user.service.UserService;
import com.yr.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderDao purchaseOrderDaoImpl;

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
    private Page<PurchaseOrderBo> page;


        @Override
        public String query(Page<PurchaseOrderBo> page) {
        this.page = page;
        //设置总条数
        page.setTotalRecord(purchaseOrderDaoImpl.getCount(page.getT()));
        //页数据
        List<PurchaseOrderBo> list = purchaseOrderDaoImpl.query(page);
        /*for ( PurchaseOrderBo purchaseOrderBo : list) {

            //根据供应商编号获取供应商对象
            Supplier supplier = supplierServices.getByCode(purchaseOrderBo.getPurchaseOrder().getSupplierCode());
            purchaseOrderBo.setSupplier(supplier);

            //根据申请人工号获取user对象
            String jobNum = purchaseOrderBo.getPurchaseOrder().getJobNumber();
            User user = userServices.getByJobNum(jobNum);
            purchaseOrderBo.setUser(user);

            //根据部门编号获取部门对象
            Department department = departmentServices.getByCode(purchaseOrderBo.getPurchaseOrder().getDepartmentCode());
            purchaseOrderBo.setDepartment(department);

            //根据供应商品code 获取供应商品对象；
            SupplierWares supplierWares = supplierWareServices.getSuppLierWareByCode(purchaseOrderBo.getPurchaseOrder().getPurchaseWareCode());
            purchaseOrderBo.setSupplierWares(supplierWares);
            //根据仓库code 获取仓库对象
            Depot depot = depotServices.getcode(purchaseOrderBo.getPurchaseOrder().getDepotCode());
            purchaseOrderBo.setDepot(depot);
        }*/
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        String json = "{\"code\": 0,\"msg\": \"\",\"count\": "+page.getTotalRecord()+",\"data\":"+jsonArray+"}";
        return json;
    }

    @Override
    public List<PurchaseOrder> queryForLists() {
        return purchaseOrderDaoImpl.queryForList();
    }


    @Override
    public PurchaseOrder getRequisitionById(Integer id) {

        return purchaseOrderDaoImpl.getRequisitionById(id);
    }

   /* @Override
    public Integer getCount(RequisitionDao requisitionDao) {
        return requisitionDaoImpl.getCount();
    }*/

    @Override
    public void add(PurchaseOrder purchaseOrder) {
        purchaseOrderDaoImpl.add(purchaseOrder);
    }

    @Override
    public void update(PurchaseOrder purchaseOrder) {
        purchaseOrderDaoImpl.update(purchaseOrder);
    }

    @Override
    public void delete(Integer id) {
        purchaseOrderDaoImpl.delete(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        purchaseOrderDaoImpl.deleteBatch(ids);
    }
}

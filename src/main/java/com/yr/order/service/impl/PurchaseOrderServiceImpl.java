package com.yr.order.service.impl;

import com.yr.entitys.bo.orderBO.purchaseOrderBO;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.page.Page;
import com.yr.order.dao.PurchaseOrderDao;
import com.yr.order.service.PurchaseOrderService;
import com.yr.util.JsonDateValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderDao purchaseOrderDaoImpl;

   /* @Override
    public Page<PurchaseOrder> query(Page<purchaseOrderBO> page) {
        Page<PurchaseOrder> page1 = new Page<PurchaseOrder>();

        //设置总条数
        Long count = purchaseOrderDaoImpl.getCount(page.getT());
        page1.setTotalRecord(count);
        //每页数
        page1.setPageSize(page.getPageSize());
        //当前页
        page1.setCurrentPage(page.getCurrentPage());
        //页数据
        page1.setPageData(purchaseOrderDaoImpl.query(page));

        return page1;
    }*/

    @Override
    public String query(Page<purchaseOrderBO> page) {
        Page<PurchaseOrder> page1 = new Page<PurchaseOrder>();

        //设置总条数
        page1.setTotalRecord(purchaseOrderDaoImpl.getCount(page.getT()));
        //每页数
        page1.setPageSize(page.getPageSize());
        //当前页
        page1.setCurrentPage(page.getCurrentPage());
        //页数据
        List<PurchaseOrder> list = purchaseOrderDaoImpl.query(page);
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

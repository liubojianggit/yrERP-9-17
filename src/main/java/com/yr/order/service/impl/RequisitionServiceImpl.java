package com.yr.order.service.impl;

import com.yr.entitys.bo.orderBO.RequisitionBO;
import com.yr.entitys.order.Requisition;
import com.yr.entitys.page.Page;
import com.yr.order.dao.ExcelDao;
import com.yr.order.dao.RequisitionDao;
import com.yr.order.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionServiceImpl implements RequisitionService{

    @Autowired
    private  RequisitionDao requisitionDaoImpl;

    @Autowired
    private ExcelDao excelDaoImpl;

    @Override
    public Page<Requisition> query(Page<RequisitionBO> page) {
        Page<Requisition> page1 = new Page<Requisition>();

        //设置总条数
        Long count = requisitionDaoImpl.getCount(page.getT());
        page1.setTotalRecord(count);
        //每页数
        page1.setPageSize(page.getPageSize());
        //当前页
        page1.setCurrentPage(page.getCurrentPage());
        //页数据
        page1.setPageData(requisitionDaoImpl.query(page));

        return page1;
    }

    @Override
    public List<Requisition> queryForLists() {
        return requisitionDaoImpl.queryForList();
    }


    @Override
    public Requisition getRequisitionById(Integer id) {

        return requisitionDaoImpl.getRequisitionById(id);
    }

   /* @Override
    public Integer getCount(RequisitionDao requisitionDao) {
        return requisitionDaoImpl.getCount();
    }*/

    @Override
    public void add(Requisition requisition) {
            requisitionDaoImpl.add(requisition);
    }

    @Override
    public void update(Requisition requisition) {
            requisitionDaoImpl.update(requisition);
    }

    @Override
    public void delete(Integer id) {
           requisitionDaoImpl.delete(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        requisitionDaoImpl.deleteBatch(ids);
    }
}

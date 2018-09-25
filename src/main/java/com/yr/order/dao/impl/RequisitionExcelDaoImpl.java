package com.yr.order.dao.impl;

import com.yr.entitys.order.Requisition;
import com.yr.order.dao.RequisitionExcelDao;
import com.yr.util.ExcelBean;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RequisitionExcelDaoImpl implements RequisitionExcelDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Requisition> queryForList() {
        String jpql ="select r from Requisition r";
        List<Requisition> list =  entityManager.createQuery(jpql).getResultList();
        return list;
    }

    /*@Override
    public List<String> getHeadName() {
        String jpql ="select COLUMN_NAME from information_schema.COLUMNS where table_name = 'requisition' ";
        List<String> list = entityManager.createQuery(jpql).getResultList();
        return list;
    }*/

    /**
     * Excel表格对应的表头信息处理类
     * @return
     */
    @Override
    public Map<Integer, List<ExcelBean>> contentExcel() {
        List<ExcelBean> excel = new ArrayList<>();
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook = null;
        //设置标题栏
        excel.add(new ExcelBean("申请ID", "id", 0));
        excel.add(new ExcelBean("申请编号", "code", 0));
        excel.add(new ExcelBean("审批人", "approver", 0));
        excel.add(new ExcelBean("收货人", "consignee", 0));
        excel.add(new ExcelBean("申请部门编号", "depa_code", 0));
        excel.add(new ExcelBean("收货仓库编号", "depot_code", 0));
        excel.add(new ExcelBean("申请人工号", "job_num", 0));
        excel.add(new ExcelBean("采购商品名称", "purc_ware_name", 0));
        excel.add(new ExcelBean("采购商品数量", "purc_ware_num", 0));
        excel.add(new ExcelBean("采购商品类型", "purc_ware_type", 0));
        excel.add(new ExcelBean("供应商编号", "supp_code", 0));
        excel.add(new ExcelBean("商品总价", "total_price", 0));
        excel.add(new ExcelBean("商品单价", "unit_price", 0));
        excel.add(new ExcelBean("采购单状态", "status", 0));
        excel.add(new ExcelBean("创建人", "createEmp", 0));
        excel.add(new ExcelBean("创建时间", "createTime", 0));
        excel.add(new ExcelBean("修改人", "updateEmp", 0));
        excel.add(new ExcelBean("修改时间", "updateTime", 0));
        map.put(0, excel);
        return map;
    }

    @Override
    public void add(Requisition requisition) {
        entityManager.persist(requisition);
    }
}

package com.yr.order.dao.impl;

import com.yr.entitys.order.PurchaseOrder;
import com.yr.order.dao.PurchaseExcelDao;
import com.yr.order.dao.PurchaseOrderDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PurchaseExcelDaoImpl implements PurchaseExcelDao {
    @PersistenceContext
    private EntityManager entityManager;
    /**
     * 查询数据库所有的用于excel 导出的数据信息
     * @return
     */
    @Override
    public List<PurchaseOrder> queryForList() {
        String jqpl = "select p from Purchase p where 1 = 1";
        List<PurchaseOrder> purchaseOrderList  = entityManager.createQuery(jqpl).getResultList();
        return purchaseOrderList;
    }
}
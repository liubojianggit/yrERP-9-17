package com.yr.order.dao;

import com.yr.entitys.order.PurchaseOrder;

import java.util.List;

public interface PurchaseExcelDao {
    /**
     * 查询数据库所有的用于excel 导出的数据信息，
     * @return list
     */
    List<PurchaseOrder> queryForList();
}

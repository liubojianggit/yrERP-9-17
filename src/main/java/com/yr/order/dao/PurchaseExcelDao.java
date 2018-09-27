package com.yr.order.dao;

import com.yr.entitys.order.PurchaseOrder;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseExcelDao {
    /**
     * 查询数据库所有的用于excel 导出的数据信息，
     * @return list
     */
    List<PurchaseOrder> queryForList();

    /**
     * 插入excel表中导入的数据
     * @param purchaseOrderList
     */
    public int addExcel(@Param("list") List<PurchaseOrder> purchaseOrderList);
}

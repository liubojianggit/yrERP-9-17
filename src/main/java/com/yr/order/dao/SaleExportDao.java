package com.yr.order.dao;

import java.util.List;

import com.yr.entitys.order.SaleOrder;

public interface SaleExportDao {

    int addExcel(List<SaleOrder> saleOrderListList);

	 /**
     * 查询数据库，并以List集合的方式返回回来
     * @return
     */
    List<SaleOrder> queryForList();

}

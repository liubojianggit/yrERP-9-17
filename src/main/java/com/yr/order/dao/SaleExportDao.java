package com.yr.order.dao;

import java.util.List;

import com.yr.entitys.order.Sale;

public interface SaleExportDao {
	 /**
     * 查询数据库，并以List集合的方式返回回来
     * @return
     */
    List<Sale> queryForList();

}

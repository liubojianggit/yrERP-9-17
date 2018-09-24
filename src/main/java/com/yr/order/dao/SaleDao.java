package com.yr.order.dao;

import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Sale;

import java.util.List;

public interface SaleDao {

    /**
     * 销售表的Excel导出
     * @return
     */
    public List<Sale> ExcelQuery();
    /**
     * 查询总条数
     * @param
     * @return Integer
     */
    Long getCount(Page<SaleBO> page);//@Param指定的是别名

    /**
     * 分页的形式查询user表的数据
     * @return List<Sale>
     */
    List<SaleBO> query(Page<SaleBO> page);

    /**
     * 添加用户信息
     * @param sale
     */
    void add(Sale sale);

    /**
     * 修改用户信息
     * @param sale
     */
    void update(Sale sale);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询用户数据
     * @param id
     * @return Sale
     */
    Sale getById(Integer id);
}
package com.yr.order.service;

import com.yr.entitys.bo.orderBO.SaleBO;
import com.yr.entitys.order.SaleOrder;
import com.yr.entitys.page.Page;
import org.springframework.web.multipart.MultipartFile;

public interface SaleService {
    /**
     * 分页的形式查询user表的数据
     * @param page
     */
    String query(Page<SaleBO> page);

    /**
     * 添加用户信息
     * @param saleOrder
     */
    void add(SaleOrder saleOrder);

    /**
     * 修改用户信息
     * @param saleOrder
     */
    void update(SaleOrder saleOrder);

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
    SaleOrder getById(Integer id);
}

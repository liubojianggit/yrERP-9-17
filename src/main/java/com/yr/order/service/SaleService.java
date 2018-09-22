package com.yr.order.service;

import com.yr.entitys.bo.orderBO.SaleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.order.Sale;
import org.springframework.web.multipart.MultipartFile;

public interface SaleService {
    boolean batchImport(String name,MultipartFile file);
    /**
     * 分页的形式查询user表的数据
     * @param page
     */
    void query(Page<SaleBo> page);

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

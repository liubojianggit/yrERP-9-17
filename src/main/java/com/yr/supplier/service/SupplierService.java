package com.yr.supplier.service;

import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;

/**
 *供应商模块
 */
public interface SupplierService {

    /**
     * 分页搜索查询供应商数据
     * @param page
     * @return
     */
    Page<SupplierBo> query(Page<SupplierBo> page);

    /**
     * 根据id查询供应商表
     * @param id
     * @return
     */
    Supplier getById(Integer id);

    /**
     * 添加供应商数据
     * @param supplier
     */
    void add(Supplier supplier);

    /**
     * 修改供应商数据
     * @param supplier
     */

    void update(Supplier supplier);
    /**
     * 删除供应商数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 判断添加供应商数据是否为null
     * @param supplier
     * @return
     */
    boolean isNullAdd(Supplier supplier);

    /**
     * 判断供应商修改数据是否为null
     * @param supplier
     * @return
     */
    boolean isNullUpdate(Supplier supplier);

}

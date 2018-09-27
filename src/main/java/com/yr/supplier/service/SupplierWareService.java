package com.yr.supplier.service;

import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.SupplierWares;

/**
 * 供应商品业务逻辑类
 */
public interface SupplierWareService {
    /**
     * 根据sw给供应商品表添加数据
     *
     * @param sw
     * @return
     */
    public boolean add(SupplierWares sw);

    /**
     * 根据id来给供应商品表删除数据
     *
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据sw来给供应商品表修改数据
     *
     * @param sw
     * @return
     */
    public boolean update(SupplierWares sw);

    /**
     * 根据page中的信息去供应商品表中查询数据
     *
     * @param page
     * @return
     */
    public Page<SupplierWareBo> query(Page<SupplierWareBo> page);

    /**
     * 根据id到供应商品表中获取对应的数据
     * @param id
     * @return
     */
    public SupplierWares getSupplierWare(Integer id);
    /**
     * 根据商品编号去查供应商品
     * @param code
     * @return
     */
    public SupplierWares getSuppLierWareByCode(String code);
}

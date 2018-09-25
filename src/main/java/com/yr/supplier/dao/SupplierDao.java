package com.yr.supplier.dao;

import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;

import java.util.List;

public interface SupplierDao {
    /**
     * 为供应商表添加数据
     * @param supplier
     */
    void add(Supplier supplier);

    /**
     * 根据id删除供应商数 据
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据对象修改供应商数据
     * @param supplier
     */
    void update(Supplier supplier);

    /**
     * 查询总数（模糊查询总数）
     * @param page
     * @return
     */
    Long getCount(Page<SupplierBo>page);

    /**
     * 根据id查询供应商数据
     * @param id
     * @return
     */
    Supplier getById(Integer id);
    /**
     * 分页查询供应商
     * @param page
     * @return
     */
    List<SupplierBo> query(Page<SupplierBo> page);


}

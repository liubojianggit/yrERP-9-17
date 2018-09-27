package com.yr.supplier.service.impl;

import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.SupplierWares;
import com.yr.supplier.dao.SupplierWareDao;
import com.yr.supplier.service.SupplierWareService;
import com.yr.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *供应商业务逻辑层的实现类
 */
@Service
public class SupplierWareServiceImpl implements SupplierWareService {
    @Autowired
    private SupplierWareDao swd;

    /**
     *根据sw来给供应商品表添加数据
     * @param sw
     * @return true表示添加成功，false表示添加失败
     */
    @Override
    public boolean add(SupplierWares sw) {
        if (isParamNull(sw)) {
            return false;
        }else{
            swd.add(sw);
            return true;
        }
    }

    /**
     *根据id到供应商品表中删除对应的数据
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return swd.delete(id);
    }

    /**
     *根据sw的数据到供应商品表中修改相应的数据
     * @param sw
     * @return
     */
    @Override
    public boolean update(SupplierWares sw) {
        if (isUpdateParamNull(sw)) {
            return false;
        }else{
            swd.update(sw)  ;
            return true;
        }
    }

    /**
     *根据page的数据到供应商品表中查询数据
     * @param page
     * @return
     */
    @Override
    public Page<SupplierWareBo> query(Page<SupplierWareBo> page) {
        System.out.println(swd.getCount(page)+"eee");
        page.setTotalRecord(swd.getCount(page));
        List<SupplierWareBo> wareList = swd.query(page);

        page.setPageData(wareList);
        return page;
    }

    /**
     *根据id到供应商品表中查询相应的数据
     * @param id
     * @return
     */
    @Override
    public SupplierWares getSupplierWare(Integer id) {
        return swd.getSupplierWare(id);
    }

    /**
     * 判断修改数据是否为null
     * @param supplierWares
     * @return
     */
    private boolean isUpdateParamNull(SupplierWares supplierWares) {
        boolean t = false;

        if (StringUtils.isNull(supplierWares.getName())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getAddr())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getBrand())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getUpdateEmp())) {
            t = true;
        }
             return t;
    }
    /**
     * 判断添加时的字段是否为null
     * @param supplierWares
     * @return
     */
    private boolean isParamNull(SupplierWares supplierWares) {
        boolean t = false;

        if (StringUtils.isNull(supplierWares.getName())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getAddr())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getBrand())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(supplierWares.getCreateEmp())) {
            t = true;
        }

        return t;
    }
}

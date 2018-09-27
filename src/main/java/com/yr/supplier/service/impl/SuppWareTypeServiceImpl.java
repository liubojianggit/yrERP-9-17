package com.yr.supplier.service.impl;

import com.yr.entitys.bo.supplierBO.SuppWareTypeBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.SuppWareType;
import com.yr.supplier.dao.SuppWareTypeDao;
import com.yr.supplier.service.SuppWareTypeService;
import com.yr.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *供应商品类型业务逻辑层的实现类
 */
@Service
public class SuppWareTypeServiceImpl implements SuppWareTypeService {
    @Autowired
    private SuppWareTypeDao swtd;
    /**
     *根据sw来给供应商品类型表添加数据
     * @param sw
     * @return true表示添加成功，false表示添加失败
     */
    @Override
    public boolean add(SuppWareType sw) {
        if (isParamNull(sw)) {
            return false;
        }else{
            swtd.add(sw);
            return true;
        }
    }
    /**
     *根据id到供应商品类型表中删除对应的数据
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return swtd.delete(id);
    }
    /**
     *根据sw的数据到供应商品类型表中修改相应的数据
     * @param sw
     * @return
     */
    @Override
    public boolean update(SuppWareType sw) {
        if (isUpdateParamNull(sw)) {
            return false;
        }else{
            swtd.update(sw)  ;
            return true;
        }
    }
    /**
     *根据page的数据到供应商品类型表中查询数据
     * @param page
     * @return
     */
    @Override
    public Page<SuppWareTypeBo> query(Page<SuppWareTypeBo> page) {
        page.setTotalRecord(swtd.getCount(page));
        List<SuppWareTypeBo> suppWareTypeList = swtd.query(page);
        page.setPageData(suppWareTypeList);
        return page;
    }
    /**
     *根据id到供应商品类型表中查询相应的数据
     * @param id
     * @return
     */
    @Override
    public SuppWareType getSuppWareType(Integer id) {
        return swtd.getSupplierWare(id);
    }
    /**
     * 判断修改数据是否为null
     * @param suppWareType
     * @return
     */
    private boolean isUpdateParamNull(SuppWareType suppWareType) {
        boolean t = false;

        if (StringUtils.isNull(suppWareType.getName())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getSupCode())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getUpdateEmp())) {
            t = true;
        }
        return t;
    }
    /**
     * 判断添加时的字段是否为null
     * @param suppWareType
     * @return
     */
    private boolean isParamNull(SuppWareType suppWareType) {
        boolean t = false;

        if (StringUtils.isNull(suppWareType.getName())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getSupCode())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(suppWareType.getCreateEmp())) {
            t = true;
        }

        return t;
    }
}

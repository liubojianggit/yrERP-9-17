package com.yr.depot.service.impl;

import com.yr.depot.service.WareTypeService;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.WareType;
import com.yr.util.StringUtils;
import com.yr.depot.dao.WareTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类型的业务逻辑实现接口类
 */
@Service
public class WareTypeServiceImpl implements WareTypeService {
    @Autowired
    private WareTypeDao wt;
    /**
     * 根据id来查询商品类型数据
     * @param id
     * @return wareType 商品类型
     */
    @Override
    public WareType getWareType(Integer id) {
        return wt.getWareType(id);
    }

    /**
     * 根据wareType来给商品类型表添加数据
     * @param wareType
     * @return
     */
    @Override
    public boolean add(WareType wareType) {
        if (isParamNull(wareType)) {
            return false;
        }else{
            wt.addWareType(wareType);
            return true;
        }
    }

    /**
     *根据id来给商品类型表删除数据
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return wt.deleteWareType(id);
    }

    /**
     *根据wareType来给商品类型表修改数据
     * @param wareType
     * @return
     */
    @Override
    public boolean update(WareType wareType) {
        if (isUpdateParamNull(wareType)) {
            return false;
        }else{
            wt.updateWareType(wareType)  ;
            return true;
        }
    }

    /**
     *根据wareType来查询商品类型表的数据
     * @param wareType
     * @return
     */
    @Override
    public Page<WareType> query(Page<WareType> wareType) {
        wareType.setTotalRecord(wt.getCount(wareType));
        List<WareType> wareList = wt.query(wareType);
        wareType.setPageData(wareList);
        return wareType;
    }


    /**
     * 判断修改数据是否为null
     * @param ware
     * @return
     */
    private boolean isUpdateParamNull(WareType ware) {
        boolean t = false;

        if (StringUtils.isNull(ware.getName())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getUpdateEmp())) {
            t = true;
        }
        return t;
    }
    /**
     * 判断添加时的字段是否为null
     * @param ware
     * @return
     */
    private boolean isParamNull(WareType ware) {
        boolean t = false;

        if (StringUtils.isNull(ware.getName())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCreateEmp())) {
            t = true;
        }

        return t;
    }
}

package com.yr.depot.service.impl;

import com.yr.depot.service.WareService;
import com.yr.entitys.bo.depotBo.WareBo;
import com.yr.entitys.depot.WareType;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.Ware;
import com.yr.util.StringUtils;
import com.yr.depot.dao.WareDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑实现类
 */
@Service
public class WareServiceImpl implements WareService {


    @Autowired
private WareDao wd;

    /**
     * 根据id来查询商品数据
     * @param id
     * @return ware 商品
     */
    @Override
    public Ware getWare(Integer id) {
        return wd.getWare(id);
    }

    /**
     * 根据前台传入WareSearchBo的数据来查询数据
     * @param ware
     * @return 返回wareSearchBo
     */
    @Override
    public Page<WareBo> query(Page<WareBo> ware) {
        ware.setTotalRecord(wd.getCount(ware));
        List<WareBo> wareList = wd.query(ware);
        ware.setPageData(wareList);
        return ware;
    }
    /**
     * 判断添加商品时是否为空
     *
     * @param ware
     *            商品
     * @return true 成功，false 失败
     */
    @Override
    public boolean add(Ware ware) {
        if (isParamNull(ware)) {
            return false;
        }else{
            wd.add(ware);
            return true;
        }
    }

    /**
     * 根据id来删除数据
     * @param id
     * @return true表示删除成功，false表示修改失败
     */
    @Override
    public boolean delete(Integer id) {
        return wd.delete(id);
    }

    /**
     * 商品修改数据
     * @param ware
     * @return true表示修改成功，false表示修改失败
     */
    @Override
    public boolean update(Ware ware) {
        if (isUpdateParamNull(ware)) {
            return false;
        }else{
            wd.update(ware)  ;
            return true;
        }
    }

    /**
     * 判断修改数据是否为null
     * @param ware
     * @return
     */
    private boolean isUpdateParamNull(Ware ware) {
        boolean t = false;

        if (StringUtils.isNull(ware.getName())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getAddr())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getBarCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getBrand())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getUpdateEmp())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getInUnitPrice())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getOutUnitPrice())) {
            t = true;
        }
        return t;
    }
    /**
     * 判断添加时的字段是否为null
     * @param ware
     * @return
     */
    private boolean isParamNull(Ware ware) {
        boolean t = false;

        if (StringUtils.isNull(ware.getName())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getAddr())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getBarCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getBrand())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCode())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getCreateEmp())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getInUnitPrice())) {
            t = true;
        }
        if (StringUtils.isNull(ware.getOutUnitPrice())) {
            t = true;
        }
        return t;
    }
    @Override
    public List<Ware> getWare() {
        return wd.getWare();
    }
}

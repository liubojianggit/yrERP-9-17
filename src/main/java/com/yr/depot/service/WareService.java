package com.yr.depot.service;

import com.yr.entitys.bo.depotBo.WareSearchBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.Ware;

/**
 * 商品业务逻辑层的接口
 */
public interface WareService {
    /**
     * 根据id来获取商品数据
     * @param id
     * @return 商品
     */
    public Ware getWare(Integer id);

    /**
     * 根据前台传入的Ware值来查询数据
     * @param ware
     * @return List<Ware>数组
     */
    public Page<WareSearchBo> query(Page<WareSearchBo> ware);

    /**
     * 添加数据
     * @param ware
     * @return true 表示添加成功；false 表示添加失败
     */
    public boolean add(Ware ware);

    /**
     * 根据ID来删除数据
     * @param id
     * @return true 表示删除成功；false 表示添加失败
     */
    public boolean delete(Integer id);

    /**
     * 根据Ware来修改数据
     * @param ware
     * @return true 表示修改成功；false表示修改失败
     */
    public boolean update(Ware ware);
}

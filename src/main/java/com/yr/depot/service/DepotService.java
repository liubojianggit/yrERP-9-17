package com.yr.depot.service;

import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;

public interface DepotService {
    /**
     * 分页搜索查询
     * @param page
     * @return
     */
    Page<Depotbo> query(Page<Depotbo> page);

    /**
     * 根据ID查数据
     * @param id
     * @return
     */
    Depot getById(Integer id);

    /**
     * 仓库添加数据
     * @param depot
     */
    void add(Depot depot);

    /**
     * 仓库修改数据
     * @param depot
     */

    void update(Depot depot);
    /**
     * 仓库删除数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 判断添加仓库数据是否为null
     * @param depot
     * @return
     */
    boolean isNullAdd(Depot depot);

    /**
     * 判断修改数据是否为null
     * @param depot
     * @return
     */
    boolean isNullUpdate(Depot depot);

}

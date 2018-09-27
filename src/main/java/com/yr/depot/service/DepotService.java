package com.yr.depot.service;

import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;

import java.util.List;

public interface DepotService {
    /**
     * 分页搜索查询
     * @param page
     * @return
     */
     String query(Page<Depotbo> page);

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
     * 删除 和批量删除
     * @param id
     */
    void delete(Integer [] id);

    /**
     * 查询仓库 提供给销售调
     * @param name
     * @return
     */
    List<Depot> getname();

    Depot getcode(String code);
}

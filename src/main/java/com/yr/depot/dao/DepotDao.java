package com.yr.depot.dao;


import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;

import java.util.List;

public interface DepotDao {
    /**
     * 为仓库添加数据
     * @param depot
     */
    void add(Depot depot);

    /**
     * 根据id删除仓库数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据对象修改仓库数据
     * @param depot
     */
    void update(Depot depot);

    /**
     * 查询总数（模糊查询总数）
     * @param depot
     * @return
     */
    Long getCount(Depotbo depot);

    /**
     * 根据id查询仓库数据
     * @param id
     * @return
     */
    Depot getById(Integer id);
    /**
     * 分页查询用户
     * @param page
     * @return
     */
    List<Depotbo> query(Page<Depotbo> page);

}

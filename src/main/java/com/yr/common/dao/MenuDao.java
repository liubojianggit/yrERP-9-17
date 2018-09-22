package com.yr.common.dao;

import com.yr.entitys.menu.Menu;

import java.util.List;

public interface MenuDao {

    /**
     * 查询
     * @return List<Menu>
     */
    List<Menu> query();
}

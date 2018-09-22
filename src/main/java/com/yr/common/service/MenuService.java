package com.yr.common.service;

import com.yr.entitys.menu.Menu;
import com.yr.entitys.user.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenuService {

    /**
     * 查询
     * @return String
     */
    String query();

    /**
     * 添加
     * @param menu
     */
    void add(Menu menu);
}

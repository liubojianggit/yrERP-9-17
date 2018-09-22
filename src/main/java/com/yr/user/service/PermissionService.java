package com.yr.user.service;

import com.yr.entitys.bo.user.PermissionBo;
import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;

public interface PermissionService {

    /**
     * 分页的形式查询role表的数据
     * @param page
     * */
    void query(Page<PermissionBo> page);

    /**
     * 添加用户信息
     * @param permission
     */
    void add(Permission permission);

    /**
     * 修改用户信息
     * @param permission
     */
    void update(Permission permission);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询用户数据
     * @param id
     * @return Role
     */
    Permission getById(Integer id);
}

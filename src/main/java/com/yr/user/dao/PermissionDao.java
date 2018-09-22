package com.yr.user.dao;

import com.yr.entitys.bo.user.PermissionBo;
import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;

import java.util.List;

public interface PermissionDao {

    /**
     * 查询总条数
     * @param page
     * @return Integer
     */
    Long getCount(Page<PermissionBo> page);//@Param指定的是别名

    /**
     * 分页的形式查询user表的数据
     * @return List<PermissionBo>
     */
    List<PermissionBo> query(Page<PermissionBo> page);

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
     * @return Permission
     */
    Permission getById(Integer id);
}

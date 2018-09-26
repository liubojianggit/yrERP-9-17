package com.yr.user.service;

import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.User;

import java.util.List;

public interface UserService {

    /**
     * 分页的形式查询user表的数据
     * @param page
     * */
    String query(Page<UserBo> page);

    /**
     * 添加用户信息
     * @param user
     */
    void add(User user);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 删除用户信息
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id查询用户数据
     * @param id
     * @return User
     */
    User getById(Integer id);

    /**
     * 根据用户id返回角色名集合
     * @param id
     * @return List<String>
     */
    List<String> getRoles(Integer id);

    /**
     * 根据用户id返回权限集合
     * @param id
     * @return List<Permission>
     */
    List<Permission> getPermissions(Integer id);

    /**
     * 给用户赋角色
     * @param id
     * @param roleIds
     */
    void setRoles(Integer id,Integer[] roleIds);

    /**
     * 根据用户名获得User对象
     * @param name
     * @return User
     */
    User getByName(String name);

    /**
     * 部门删除时调用,根据部门编号删除用户
     */
    void delete(String department);
}

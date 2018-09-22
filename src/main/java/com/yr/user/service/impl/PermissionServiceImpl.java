package com.yr.user.service.impl;
import com.yr.entitys.bo.user.PermissionBo;
import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;
import com.yr.user.dao.PermissionDao;
import com.yr.user.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionServiceImpl")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    @Qualifier("permissionDaoImpl")
    private PermissionDao permissionDao;

    /**
     * 分页的形式查询role表的数据
     * @param page
     */
    public void query(Page<PermissionBo> page){
        page.setTotalRecord(permissionDao.getCount(page));//查询总条数加入page中
        List<PermissionBo> list = permissionDao.query(page);//分页查询的数据
        page.setPageData(list);//将集合放入page中
    }

    /**
     * 添加权限信息
     * @param permission
     */
    @Transactional
    public void add(Permission permission){
        permission.setMethod(permission.getMethod().toUpperCase());//将method转为大写
        permissionDao.add(permission);
    }

    /**
     * 修改权限信息
     * @param permission
     */
    @Transactional
    public void update(Permission permission){
        permissionDao.update(permission);
    }

    /**
     * 删除权限信息
     * @param id
     */
    @Transactional
    public void delete(Integer id){
        permissionDao.delete(id);
    }

    /**
     * 根据id查询权限数据
     * @param id
     * @return Role
     */
    public Permission getById(Integer id){
        return permissionDao.getById(id);
    }
}

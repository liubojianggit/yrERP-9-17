package com.yr.user.dao.impl;

import com.yr.entitys.bo.user.PermissionBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.user.dao.PermissionDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.Permissions;
import java.util.List;

@Repository("permissionDaoImpl")
public class PermissionDaoImpl implements PermissionDao {
    //如何获取到和当前事务关联的 EntityManager 对象呢 ?通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * 查询总条数
     * @param page
     * @return Integer
     */
    public Long getCount(Page<PermissionBo> page){//@Param指定的是别名
        String jpql = "select count(*) from Permission p where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getName())){//判断是否为null和空
            jpql += "and r.name like :name ";
        }
        /*if(!StringUtils.isEmpty(page.getT().getStartBirthday())){
            jpql += "and u.birthday >= :startBirthday ";
        }
        if(!StringUtils.isEmpty(page.getT().getEndBirthday())){
            jpql += "and u.birthday <= :endBirthday ";
        }
        if(!StringUtils.isEmpty(page.getT().getUser().getAddr())){
            jpql += "and u.addr like :addr ";
        }*/
        Query query = entityManager.createQuery(jpql);
        if(!StringUtils.isEmpty(page.getT().getName())){
            query.setParameter("name", "%"+page.getT().getName()+"%");
        }
        /*if(!StringUtils.isEmpty(page.getT().getStartBirthday())){
            query.setParameter("startBirthday", DateUtils.toDate(page.getT().getStartBirthday()));
        }
        if(!StringUtils.isEmpty(page.getT().getEndBirthday())){
            query.setParameter("endBirthday", DateUtils.toDate(page.getT().getEndBirthday()));
        }
        if(!StringUtils.isEmpty(page.getT().getUser().getAddr())){
            query.setParameter("addr", "%"+page.getT().getUser().getAddr()+"%");
        }*/
        Long count = (Long)query.getSingleResult();//获取到结果
        return count;//将long转int
    }

    /**
     * 分页的形式查询权限表的数据
     * @return List<PermissionBo>
     */
    public List<PermissionBo> query(Page<PermissionBo> page){
        String jpql = "select p from Permission p where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getName())){//判断是否为null和空
            jpql += "and r.name like :name ";
        }
        /*if(!StringUtils.isEmpty(page.getT().getStartBirthday())){
            jpql += "and u.birthday >= :startBirthday ";
        }
        if(!StringUtils.isEmpty(page.getT().getEndBirthday())){
            jpql += "and u.birthday <= :endBirthday ";
        }
        if(!StringUtils.isEmpty(page.getT().getUser().getAddr())){
            jpql += "and u.addr like :addr ";
        }
        if(page.getT().getOrder() == 0){
            jpql += "order by u.id asc ";
        }else if(page.getT().getOrder() == 1){
            jpql += "order by u.id desc ";
        }*/
        Query query = entityManager.createQuery(jpql);
        if(!StringUtils.isEmpty(page.getT().getName())){
            query.setParameter("name", "%"+page.getT().getName()+"%");
        }
       /* if(!StringUtils.isEmpty(page.getT().getStartBirthday())){
            query.setParameter("startBirthday", DateUtils.toDate(page.getT().getStartBirthday()));
        }
        if(!StringUtils.isEmpty(page.getT().getEndBirthday())){
            query.setParameter("endBirthday", DateUtils.toDate(page.getT().getEndBirthday()));//转sql date
        }
        if(!StringUtils.isEmpty(page.getT().getUser().getAddr())){
            query.setParameter("addr", "%"+page.getT().getUser().getAddr()+"%");
        }*/
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<PermissionBo> list = query.getResultList();//获得分页后的数据集合
        return list;
    }

    /**
     * 添加权限信息
     * @param permission
     */
    public void add(Permission permission){
        /*String sql = "insert into u_permission(method,name,sup_id,url)values(?1,?2,?3,?4)";
        Query query = entityManager.createQuery(sql).setParameter(1, permission.getMethod()).setParameter(2, permission.getName()).setParameter(3,permission.getSupId()).setParameter(4,permission.getUrl());
        query.executeUpdate();*/
        entityManager.persist(permission);
    }

    /**
     * 修改用户信息
     * @param permission
     */
    public void update(Permission permission){
        /*String jpql = "update Permission p set p.method=?1,p.name=?2,p.sup_id=?3,p.url=?4 where p.id=?5";
        Query query = entityManager.createQuery(jpql).setParameter(1, permission.getMethod()).setParameter(2, permission.getName()).setParameter(3, permission.getSupId()).setParameter(4,permission.getUrl()).setParameter(5,permission.getId());
        query.executeUpdate();*/
        entityManager.merge(permission);
    }

    /**
     * 删除权限信息
     * @param id
     */
    public void delete(Integer id){
        /*String jpql = "delete from Permission p where p.id = ?1";
        Query query = entityManager.createQuery(jpql).setParameter(1, id);
        query.executeUpdate();*/
        Permission permission = entityManager.find(Permission.class,id);
        entityManager.remove(permission);
    }

    /**
     * 根据id查询权限数据
     * @param id
     * @return ole
     */
    public Permission getById(Integer id){
        /*String jpql = "select p from Permission p where p.id = ?1";
        Permission permission = (Permission) entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();
        return permission;*/
        Permission permission = entityManager.find(Permission.class,id);
        return permission;
    }

    /**
     * 回显角色具有哪些权限
     */
    public List<PermissionBo> getPermission(Integer id){
        String sql = "select p.id,rp.rid roleId,p.name,p.url,p.sup_id,ifnull(rp.rid,0) mark from u_permission p left join (select * from u_role_permission where rid = 1) rp on p.id = rp.pid";
        Query query = entityManager.createNativeQuery(sql).setParameter(1,id);
        List<PermissionBo> permissions = query.getResultList();
        return permissions;
    }

    /**
     * 根据父权限获得子权限
     * @param rid 角色id
     * @param pid 权限id
     * @return List<PermissionBo>
     */
    public List<PermissionBo> getchildren(Integer rid, Integer pid){
        String sql = "select p.id,rp.rid roleId,p.name,p.url,p.sup_id,ifnull(rp.rid,0) mark from (select * from u_permission where sup_id = ?1) p left join (select * from u_role_permission where rid = ?2) rp on p.id = rp.pid";
        Query query = entityManager.createQuery(sql).setParameter(1,pid).setParameter(2,rid);
        List<PermissionBo> permissions = query.getResultList();
        return permissions;
    }

    /**
     * 根据子id获取所有父级id
     * @param id
     * @return Permission
     */
    public Permission getParent(Integer id){
        String sql = "select * from u_permission where id = (select sup_id from u_permission where id = ?1)";
        Query query = entityManager.createNativeQuery(sql).setParameter(1,id);
        Permission permission = (Permission)query.getSingleResult();
        return permission;
    }

}

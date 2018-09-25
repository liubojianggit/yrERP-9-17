package com.yr.user.dao.impl;

import com.yr.entitys.bo.user.RoleBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;
import com.yr.user.dao.RoleDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("roleDaoImpl")
public class RoleDaoImpl implements RoleDao {

    //如何获取到和当前事务关联的 EntityManager 对象呢 ?通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 查询总条数
     * @param page
     * @return Integer
     */
    public Long getCount(Page<RoleBo> page){//@Param指定的是别名
        String jpql = "select count(*) from Role r where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getRole().getName())){//判断是否为null和空
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
        if(!StringUtils.isEmpty(page.getT().getRole().getName())){
            query.setParameter("name", "%"+page.getT().getRole().getName()+"%");
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
     * 分页的形式查询角色表的数据
     * @return List<RoleBo>
     */
    public List<RoleBo> query(Page<RoleBo> page){
        String jpql = "select r from Role r where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getRole().getName())){//判断是否为null和空
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
        if(!StringUtils.isEmpty(page.getT().getRole().getName())){
            query.setParameter("name", "%"+page.getT().getRole().getName()+"%");
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
        List<RoleBo> list = query.getResultList();//获得分页后的数据集合
        return list;
    }

    /**
     * 添加角色信息
     * @param role
     */
    public void add(Role role){
        entityManager.persist(role);
        /*String sql = "insert into u_role(code,name)values(?1,?2)";
        Query query = entityManager.createQuery(sql).setParameter(1, role.getCode()).setParameter(2, role.getName());
        query.executeUpdate();*/
    }

    /**
     * 修改角色信息
     * @param role
     */
    public void update(Role role){
        entityManager.merge(role);
        /*String jpql = "update Role r set r.code=?1,r.name=?2 where r.id=?3";
        Query query = entityManager.createQuery(jpql).setParameter(1, role.getCode()).setParameter(2, role.getName()).setParameter(3, role.getId());
        query.executeUpdate();*/
    }

    /**
     * 删除角色信息
     * @param id
     */
    public void delete(Integer id){
        Role role = entityManager.find(Role.class,id);
        entityManager.remove(role);
        /*String jpql = "delete from Role r where r.id = ?1";
        Query query = entityManager.createQuery(jpql).setParameter(1, id);
        query.executeUpdate();*/
    }

    /**
     * 根据id查询角色数据
     * @param id
     * @return ole
     */
    public Role getById(Integer id){
        /*String jpql = "select r from Role r where r.id = ?1";
        Role role = (Role) entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();*/
        Role role = entityManager.find(Role.class,id);
        return role;
    }

    /**
     * 删除权限关联表
     * @param id
     */
    public void deletePermissions(Integer id){
        Role role = entityManager.find(Role.class, id);//获得User对象
        Set<Permission> permission = role.getPermission();//获得user对象关联的role对象
        role.getPermission().remove(permission);//将所有的用户关联的role删除,这时会将关联表一起删除，如果设置了remove，角色也会被删除
    }

    /**
     * 将角色关联权限
     * @param id
     * @param permissionIds
     */
    public void addPermissions(Integer id,Integer[] permissionIds){
        Role role = entityManager.find(Role.class,id);
        Set<Permission> permissions = new HashSet<>();
        for (int i=0;i<permissionIds.length;i++){
            Permission permission = entityManager.find(Permission.class,permissionIds[i]);
            permissions.add(permission);
        }
        role.setPermission(permissions);
        entityManager.merge(role);
    }
}

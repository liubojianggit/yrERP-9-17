package com.yr.user.dao.impl;


import com.yr.entitys.bo.user.UserBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.user.Permission;
import com.yr.entitys.user.Role;
import com.yr.entitys.user.User;
import com.yr.user.dao.UserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    //如何获取到和当前事务关联的 EntityManager 对象呢 ?通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 查询总条数
     * @param page
     * @return Integer
     */
    public Long getCount(Page<UserBo> page){//@Param指定的是别名
        String jpql = "select count(*) from User u where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getUser().getName())){//判断是否为null和空
            jpql += "and u.name like :name ";
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
        if(!StringUtils.isEmpty(page.getT().getUser().getName())){
            query.setParameter("name", "%"+page.getT().getUser().getName()+"%");
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
     * 分页的形式查询user表的数据
     * @return List<User>
     */
    public List<UserBo> query(Page<UserBo> page){
        String jpql = "select u from User u where 1 = 1 ";
        if(!StringUtils.isEmpty(page.getT().getUser().getName())){//判断是否为null和空
            jpql += "and u.name like :name ";
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
        if(!StringUtils.isEmpty(page.getT().getUser().getName())){
            query.setParameter("name", "%"+page.getT().getUser().getName()+"%");
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
        List<UserBo> list = query.getResultList();//获得分页后的数据集合
        return list;
    }

    /**
     * 添加用户信息
     * @param user
     */
    public void add(User user){
        entityManager.persist(user);
        /*String sql = "insert into user(photo,jobNum,depaCode,name,sex,birthday,addr,phoneNumber,states,passwrod)values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)";
        Query query = entityManager.createQuery(sql).setParameter(1, user.getPhoto()).setParameter(2, user.getJobNum()).setParameter(3, user.getDepaCode())
                .setParameter(4, user.getName()).setParameter(5, user.getSex()).setParameter(6, user.getBirthday()).setParameter(7, user.getAddr()).
                        setParameter(8, user.getPhoneNumber()).setParameter(9, user.getStates()).setParameter(10, user.getPassword());
        query.executeUpdate();*/
    }

    /**
     * 修改用户信息
     * @param user
     */
    public void update(User user){
        entityManager.merge(user);
        /*String jpql = "update User u set u.photo=?1,u.jobNum=?2,u.depaCode=?3,u.name=?4,u.sex=?5,u.birthday=?6,u.addr=?7,u.phoneNumber=?8,u.states=?9,u.password=?10 where u.id=?11";
        Query query = entityManager.createQuery(jpql).setParameter(1, user.getPhoto()).setParameter(2, user.getJobNum()).setParameter(3, user.getDepaCode())
                .setParameter(4, user.getName()).setParameter(5, user.getSex()).setParameter(6, user.getBirthday()).setParameter(7, user.getAddr()).
                setParameter(8, user.getPhoneNumber()).setParameter(9, user.getStates()).setParameter(10, user.getPassword()).setParameter(11, user.getId());
        query.executeUpdate();*/
    }

    /**
     * 删除用户信息
     * @param id
     */
    public void delete(Integer id){
        /*String jpql = "delete from User u where u.id = ?1";
        Query query = entityManager.createQuery(jpql).setParameter(1, id);
        query.executeUpdate();*/
        User user = entityManager.find(User.class,id);
        entityManager.remove(user);
    }

    /**
     * 根据id查询用户数据
     * @param id
     * @return User
     */
    public User getById(Integer id){
        User user =  entityManager.find(User.class, id);
        /*String jpql = "select u from User u where u.id = ?1";
        User user = (User)entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();*/
        return user;
    }

    /**
     * 根据用户id返回角色名集合
     * @param id
     * @return User
     */
    public List<String>  getRoles(Integer id){
        String jpql = "select r.name from User u inner join u.role r where u.id = ?1";
        List<String> list = entityManager.createQuery(jpql).setParameter(1,id).getResultList();
        return null;
    }

    /**
     * 根据用户id返回权限集合
     * @param id
     * @return List<Permission>
     */
    public List<Permission> getPermissions(Integer id){
        String jpql = "select p.name,p.method from User u inner join u.role r inner join r.permission p where u.id = ?1";
        List<Permission> list = entityManager.createQuery(jpql).setParameter(1,id).getResultList();
        return list;
    }

    /**
     * 删除角色关联表
     * @param id
     */
    public void deleteRoles(Integer id){
        User user = entityManager.find(User.class, id);//获得User对象
        Set<Role> role = user.getRole();//获得user对象关联的role对象
        user.getRole().remove(role);//将所有的用户关联的role删除,这时会将关联表一起删除，如果设置了remove，角色也会被删除
        //entityManager.merge(role);//所以这里又将角色添加进去
    }

    /**
     * 将用户关联角色
     * @param id
     * @param roleIds
     */
    public void addRoles(Integer id,Integer[] roleIds){
        User user = entityManager.find(User.class,id);
        Set<Role> role = new HashSet<>();
        for (int i=0;i<roleIds.length;i++){
            Role role1 = entityManager.find(Role.class,roleIds[i]);
            role.add(role1);
        }
        user.setRole(role);
        entityManager.merge(user);
    }

    /**
     * 根据用户名获得User对象
     * @param name
     * @return User
     */
    public User getByName(String name){
        String jpql = "select u from User u where u.name = ?1";
        User user = (User)entityManager.createQuery(jpql).setParameter(1,name).getSingleResult();
        return user;
    }
}

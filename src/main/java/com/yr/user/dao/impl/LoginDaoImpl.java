package com.yr.user.dao.impl;

import com.yr.user.dao.LoginDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("loginDaoImpl")
public class LoginDaoImpl implements LoginDao {

    @PersistenceContext//注解来标记成员变量
    private EntityManager entityManager;


    //查看用户状态
    @Override
    public int userstates( Integer states) {
        String sql="select * from u_user where states=?1";
        Integer user = (Integer)entityManager.createQuery(sql).setParameter(1, states).getSingleResult();
        return user;
    }


    //验证用户名密码是否正确
    @Override
    public int getMatchCount(String name, String password) {
        String sql="select * from u_user where name=? and password=?";
        Integer user = (Integer)entityManager.createQuery(sql).setParameter(password, name).getSingleResult();
        return user;
    }
}

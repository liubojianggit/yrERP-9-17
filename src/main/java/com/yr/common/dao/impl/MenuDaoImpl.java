package com.yr.common.dao.impl;

import com.yr.common.dao.BaseDao;
import com.yr.common.dao.MenuDao;
import com.yr.entitys.bo.menuBO.MenuBO;
import com.yr.entitys.menu.Menu;
import com.yr.entitys.user.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class MenuDaoImpl implements MenuDao {
    //如何获取到和当前事务关联的 EntityManager 对象呢 ?通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Menu> query() {
        String jpql = "select m from Menu m";
        Query query = entityManager.createQuery(jpql);
        List<Menu> list = query.getResultList();
        return list;
    }

    @Override
    public Menu getOneMenu(Integer id) {
        Menu menu=entityManager.find(Menu.class,id);
        return menu;
    }

    @Override
    public void add(MenuBO menuBO,User loginUser) {
        menuBO.getMenu().setCreateEmp("刘柏江");
        menuBO.getMenu().setCreateTime(new Timestamp(System.currentTimeMillis()));
        menuBO.getMenu().setUpdateEmp("刘柏江");
        menuBO.getMenu().setUpdateTime(new Timestamp(System.currentTimeMillis()));
        menuBO.getMenu().setMethod("GET");//应为所有的菜单请求后台的方式只能是GET，所有这里写死了
        entityManager.persist(menuBO.getMenu());
    }

    //根据fontClass查询图标
    @Override
    public String queryIcon(String fontClass) {
        String sql = "select i.unicode from Icon i where i.fontClass=?";
        Query query = entityManager.createQuery(sql);
        query.setParameter(1,fontClass);
        String str = (String) query.getSingleResult();
        return str;
    }

    //查询图标unicode
    @Override
    public String queryIconUnicode(String str) {
        String sql = "select i.unicode from Icon i where i.fontClass=? or i.unicode=?";
        Query query = entityManager.createQuery(sql);
        query.setParameter(1,str);
        query.setParameter(2,str);
        String unicodeStr = (String) query.getSingleResult();
        return unicodeStr;
    }



    @Override
    public Long queryUrl(Menu menu) {
        String sql = "select count(m) from Menu m where m.url=? or m.name=?";
        Query query = entityManager.createQuery(sql);
        query.setParameter(1,menu.getUrl());
        query.setParameter(2,menu.getName());
        Long urlCount = (Long) query.getSingleResult();
        return urlCount;
    }

    @Override
    public void delete(Integer id) {
        Menu menu = entityManager.find(Menu.class,id);
        entityManager.remove(menu);
    }

    @Override
    public void update(Menu menu) {
        entityManager.merge(menu);
    }
}
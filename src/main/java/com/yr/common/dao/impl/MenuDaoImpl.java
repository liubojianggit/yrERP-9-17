package com.yr.common.dao.impl;

import com.yr.common.dao.BaseDao;
import com.yr.common.dao.MenuDao;
import com.yr.entitys.menu.Menu;
import com.yr.entitys.user.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
/*"contentManagement": [
		{
			"title": "文章列表",
			"icon": "icon-text",
			"href": "page/news/newsList.html",
			"spread": false
		},
		{
			"title": "图片管理",
			"icon": "&#xe634;",
			"href": "page/img/images.html",
			"spread": false
		},
		{
			"title": "其他页面",
			"icon": "&#xe630;",
			"href": "",
			"spread": false,
			"children": [
				{
					"title": "404页面",
					"icon": "&#xe61c;",
					"href": "page/404.html",
					"spread": false
				},
				{
					"title": "登录",
					"icon": "&#xe609;",
					"href": "page/login/login.html",
					"spread": false,
					"target": "_blank"
				}
			]
		}
	]
* */
@Repository
public class MenuDaoImpl extends BaseDao<Menu> implements MenuDao {
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
}
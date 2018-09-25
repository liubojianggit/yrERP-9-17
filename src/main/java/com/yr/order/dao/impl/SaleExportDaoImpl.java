package com.yr.order.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yr.entitys.order.SaleOrder;
import org.springframework.stereotype.Repository;

import com.yr.order.dao.SaleExportDao;
@Repository
public class SaleExportDaoImpl implements SaleExportDao{
	
	 //获取到和当前事务关联的 EntityManager对象,通过 @PersistenceContext 注解来标记成员变量
    @PersistenceContext
    private EntityManager entityManager;
	
	
	 @Override
		public List<SaleOrder> queryForList() {
	    	String jpql = "select s from SaleOrder s ";
	    	  Query query = entityManager.createQuery(jpql);
	          List<SaleOrder> list = query.getResultList();
	          return list;
	    }
	    
	 
}

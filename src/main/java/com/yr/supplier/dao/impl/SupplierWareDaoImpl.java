package com.yr.supplier.dao.impl;

import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;
import com.yr.entitys.supplier.SupplierWares;
import com.yr.supplier.dao.SupplierWareDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 *供应商品dao层的实现类
 */
@Repository
public class SupplierWareDaoImpl implements SupplierWareDao {


    //如何获取到和当前事务关联的 EntityManager 对象呢 ?
    //通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 根据sw来给供应商品表中添加数据
     * @param sw
     * @return
     */
    @Override
    public void add(SupplierWareBo sw) {
        entityManager.persist(sw);
    }

    /**
     * 根据id到供应商品表中删除指定数据
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
        String jpq= "delete from SupplierWares s where s.id = ?1";
        Query query = entityManager.createQuery(jpq).setParameter(1, id);
        query.executeUpdate();
    }

    /**
     * 根据sw到供应商品表中修改相应数据
     * @param sw
     * @return
     */
    @Override
    public void update(SupplierWareBo sw) {
        entityManager.merge(sw.getSupplierWare());
    }

    /**
     * 跟句page中的查询条件到供应商品表中查询相应数据
     * @param page
     * @return
     */
    @Override
    public List<SupplierWareBo> query(Page<SupplierWareBo> page) {
        String jpql=("select s from SupplierWares s where 1=1 ");
        String brand = page.getT().getBrand();
        String name = page.getT().getName();
        String type = page.getT().getType();
        if (brand != null && brand.equals("")) {
            jpql+="and s.brand like '%" + brand + "%'";
        }
        if (type != null && type.equals("")) {
            jpql+="and s.type like '%" + type + "%'";
        }
        if (name != null && name.equals("")) {
            jpql+="and s.name like '&" + name + "&'";
        }
        Query query =entityManager.createQuery(jpql);
        if (brand != null && brand.equals("")) {
            query.setParameter("brand","%"+brand.trim()+"%");
        }
        if (type != null && type.equals("")) {
            query.setParameter("type","%"+type.trim()+"%");
        }
        if (name != null && name.equals("")) {
            query.setParameter("name","%"+name.trim()+"%");
        }
        System.out.println(jpql);
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<SupplierWareBo> list = query.getResultList();
        return list;
    }

    /**
     * 根据id到供应商品表中获取指定数据
     * @param id
     * @return
     */
    @Override
    public SupplierWares getById(Integer id) {
        SupplierWares supplierWares = entityManager.find(SupplierWares.class,id);
        return supplierWares;
    }

    /**
     * 根据page中的查询条件到供应商品表中得到数据的数目
     * @param page
     * @return
     */
    @Override
    public Long getCount(Page<SupplierWareBo> page) {
        String jpql="select count(*) from SupplierWares s  where 1=1";
        String brand = page.getT().getBrand();
        String name = page.getT().getName();
        String type = page.getT().getType();
        if (brand != null && brand.equals("")) {
            jpql+="and s.brand like '%" + brand + "%'";
        }
        if (type != null && type.equals("")) {
            jpql+="and s.type like '%" + type + "%'";
        }
        if (name != null && name.equals("")) {
            jpql+="and s.name like '&" + name + "&'";
        }
        Query query =entityManager.createQuery(jpql);
        if (brand != null && brand.equals("")) {
            query.setParameter("brand","%"+brand.trim()+"%");
        }
        if (type != null && type.equals("")) {
            query.setParameter("type","%"+type.trim()+"%");
        }
        if (name != null && name.equals("")) {
            query.setParameter("name","%"+name.trim()+"%");
        }
        Long count =(Long)query.getSingleResult();
        return count;
    }

    /**
     * 根据编号去查商品
     * @param code
     * @return
     */
    @Override
    public SupplierWares getSuppLierWareByCode(String code) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select s from SupplierWares s where code =?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, code);
        SupplierWares supplierWare = (SupplierWares) query.getSingleResult();
        return supplierWare;
    }
}

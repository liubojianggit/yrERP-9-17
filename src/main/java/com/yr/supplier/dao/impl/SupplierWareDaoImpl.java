package com.yr.supplier.dao.impl;

import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.supplierWares;
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
    public boolean add(supplierWares sw) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into supp_wares(name,code,supp_code,type,total_inventory,unit_price,brand,addr,createTime,createEmp) values(?,?,?,?,?,?,?,?,?,?)");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, sw.getName());
        query.setParameter(2, sw.getCode());
        query.setParameter(3, sw.getSuppCode());
        query.setParameter(4, sw.getType());
        query.setParameter(5, sw.getTotalInventory());
        query.setParameter(6, sw.getUnitPrice());
        query.setParameter(7, sw.getBrand());
        query.setParameter(8, sw.getAddr());
        query.setParameter(9, new Date());
        query.setParameter(10, sw.getCreateEmp());
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据id到供应商品表中删除指定数据
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("delete s from supplierWares s where s.id = ?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, id);
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据sw到供应商品表中修改相应数据
     * @param sw
     * @return
     */
    @Override
    public boolean update(supplierWares sw) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("update supplierWares set name = ?,code = ?, supp_code = ?,type=?,total_inventory=?,unit_price=?," +
                "brand = ?,addr = ?, updateEmp = ?,updateTime = ?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, sw.getName());
        query.setParameter(2, sw.getCode());
        query.setParameter(3, sw.getSuppCode());
        query.setParameter(4, sw.getType());
        query.setParameter(5, sw.getTotalInventory());
        query.setParameter(6, sw.getUnitPrice());
        query.setParameter(7, sw.getBrand());
        query.setParameter(8, sw.getAddr());
        query.setParameter(9, new Date());
        query.setParameter(10, sw.getUpdateEmp());
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 跟句page中的查询条件到供应商品表中查询相应数据
     * @param page
     * @return
     */
    @Override
    public List<SupplierWareBo> query(Page<SupplierWareBo> page) {


        StringBuffer jpql = new StringBuffer();
        jpql.append("select s from supplierWares s where 1=1");
        String brand = page.getT().getBrand();
        String name = page.getT().getName();
        String type = page.getT().getType();
        if (brand != null && brand.equals("")) {
            jpql.append("and brand like '%" + brand + "%'");
        }
        if (type != null && type.equals("")) {
            jpql.append("and type like '%" + type + "%'");
        }
        if (name != null && name.equals("")) {
            jpql.append("and name like '&" + name + "&'");
        }
        jpql.append("order by id desc");
        Query query = entityManager.createQuery(jpql.toString());
        query.setFirstResult(page.getCurrentPage());
        query.setMaxResults(page.getPageSize());
        List<SupplierWareBo> supplierWare = query.getResultList();
        return supplierWare;
    }

    /**
     * 根据id到供应商品表中获取指定数据
     * @param id
     * @return
     */
    @Override
    public supplierWares getSupplierWare(Integer id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select s from supplierWares s where id =?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, id);
        supplierWares supplierWare = (supplierWares) query.getSingleResult();
        return supplierWare;
    }

    /**
     * 根据page中的查询条件到供应商品表中得到数据的数目
     * @param page
     * @return
     */
    @Override
    public Long getCount(Page<SupplierWareBo> page) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select count(*) from supplierWares s where 1=1");
        String brand = page.getT().getBrand();
        String name = page.getT().getName();
        String type = page.getT().getType();
        if (brand != null && brand.equals("")) {
            jpql.append("and brand like '%" + brand + "%'");
        }
        if (type != null && type.equals("")) {
            jpql.append("and type like '%" + type + "%'");
        }
        if (name != null && name.equals("")) {
            jpql.append("and name like '&" + name + "&'");
        }
        Query query = entityManager.createQuery(jpql.toString());

        Long count = (Long) query.getSingleResult();
        return count;
    }
}

package com.yr.supplier.dao.impl;

import com.yr.entitys.bo.supplierBO.SupplierWareBo;
import com.yr.entitys.page.Page;
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
    public boolean add(SupplierWares sw) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into supp_wares(name,code,type,total_inventory,unit_price,brand,addr,createTime,createEmp) values(?,?,?,?,?,?,?,?,?,?)");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, sw.getName());
        query.setParameter(2, sw.getCode());
        query.setParameter(3, sw.getType());
        query.setParameter(4, sw.getTotalInventory());
        query.setParameter(5, sw.getUnitPrice());
        query.setParameter(6, sw.getBrand());
        query.setParameter(7, sw.getAddr());
        query.setParameter(8, new Date());
        query.setParameter(9, sw.getCreateEmp());
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
        jpql.append("delete s from SupplierWares s where s.id = ?");
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
    public boolean update(SupplierWares sw) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("update SupplierWares set name = ?,code = ?,type=?,total_inventory=?,unit_price=?," +
                "brand = ?,addr = ?, updateEmp = ?,updateTime = ?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, sw.getName());
        query.setParameter(2, sw.getCode());
        query.setParameter(3, sw.getType());
        query.setParameter(4, sw.getTotalInventory());
        query.setParameter(5, sw.getUnitPrice());
        query.setParameter(6, sw.getBrand());
        query.setParameter(7, sw.getAddr());
        query.setParameter(8, new Date());
        query.setParameter(9, sw.getUpdateEmp());
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
        jpql.append("select s from SupplierWares s where 1=1");
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
        System.out.println(supplierWare);
        return supplierWare;
    }

    /**
     * 根据id到供应商品表中获取指定数据
     * @param id
     * @return
     */
    @Override
    public SupplierWares getSupplierWare(Integer id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select s from SupplierWares s where id =?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, id);
        SupplierWares supplierWare = (SupplierWares) query.getSingleResult();
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
        jpql.append("select count(*) from SupplierWares s where 1=1");
       System.out.println("3");
        System.out.println(page.getT().getBrand());
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

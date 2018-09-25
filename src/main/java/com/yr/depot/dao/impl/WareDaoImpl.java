package com.yr.depot.dao.impl;

import com.yr.depot.dao.WareDao;
import com.yr.entitys.bo.depotBo.WareBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.depot.Ware;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * 商品dao接口实现类
 */
@Repository
public class WareDaoImpl implements WareDao {
    //如何获取到和当前事务关联的 EntityManager 对象呢 ?
    //通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 根据id来查询商品数据
     *
     * @param id
     * @return Ware商品
     */
    @Override
    public Ware getWare(Integer id) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select w from Ware w where w.id=?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1, id);
        Ware ware = (Ware) query.getSingleResult();
        return ware;
    }

    /**
     * 根据拓展类中的字段来查询数据
     * @param ware
     * @return List ware 商品
     */
    @Override
    public List<WareBo> query(Page<WareBo> ware) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select w from Ware w where 1=1");
        String addr = ware.getT().getAddr();
        String code = ware.getT().getCode();
        String name = ware.getT().getName();
        String type = ware.getT().getType();
        Double minprice = ware.getT().getMinOutUnitPrice();
        Double maxprice = ware.getT().getMaxOutUnitPrice();
        if (addr != null && addr.equals("")) {
            jpql.append("and addr like '%" + addr + "%'");
        }
        if (code != null && code.equals("")) {
            jpql.append("and code like '%" + code + "%'");
        }
        if (name != null && name.equals("")) {
            jpql.append("and name like '&" + name + "&'");
        }
        if (type != null && type.equals("")) {
            jpql.append("and type like '%" + type + "%'");
        }
        if (minprice != null && minprice.equals("")) {
            jpql.append("and '" + minprice + "' >= In_unit_price");
        }
        if (maxprice != null && maxprice.equals("")) {
            jpql.append("and In_unit_price >='" + maxprice + "'");
        }
        jpql.append("order by id desc");
        Query query = entityManager.createQuery(jpql.toString());
        query.setFirstResult(ware.getCurrentPage());
        query.setMaxResults(ware.getPageSize());
        List<WareBo> wares = query.getResultList();
        return wares;
    }

    @Override
    public boolean add(Ware ware) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("insert into wares(type,code,ware_photo,name,addr,brand,out_unit_price,bar_code," +
                "total_inventory,createTime,createEmp,remark)");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1,ware.getType());
        query.setParameter(2,ware.getCode());
        query.setParameter(3,ware.getWarePhoto());
        query.setParameter(4,ware.getName());
        query.setParameter(5,ware.getAddr());
        query.setParameter(6,ware.getBrand());
        query.setParameter(7,ware.getOutUnitPrice());
        query.setParameter(8,ware.getBarCode());
        query.setParameter(9,ware.getTotalInventory());
        query.setParameter(10,new Date());
        query.setParameter(11,ware.getCreateEmp());
        query.setParameter(12,ware.getRemark());
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean delete(Integer id) {
       StringBuffer jpql = new StringBuffer();
       jpql.append("delete from Ware where id = ?");
       Query query = entityManager.createQuery(jpql.toString());
       query.setParameter(1,id);
        try {
            query.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Ware ware) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("update Ware set type = ?,code=?,ware_photo=?,name=?,addr=?,brand=?," +
                "out_unit_price=?,bar_code=?,total_inventory=?,updateTime=?,updateEmp=?," +
                "remark=? where id = ?");
        Query query = entityManager.createQuery(jpql.toString());
        query.setParameter(1,ware.getType());
        query.setParameter(2,ware.getCode());
        query.setParameter(3,ware.getWarePhoto());
        query.setParameter(4,ware.getName());
        query.setParameter(5,ware.getAddr());
        query.setParameter(6,ware.getBrand());
        query.setParameter(7,ware.getOutUnitPrice());
        query.setParameter(8,ware.getBarCode());
        query.setParameter(9,ware.getTotalInventory());
        query.setParameter(10,new Date());
        query.setParameter(11,ware.getUpdateEmp());
        query.setParameter(12,ware.getRemark());
        query.setParameter(13,ware.getId());
        try {
            query.executeUpdate();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getCount(Page<WareBo> ware) {
        StringBuffer jpql = new StringBuffer();
        jpql.append("select count(*) from Ware where 1=1");
        String addr = ware.getT().getAddr();
        String code = ware.getT().getCode();
        String name = ware.getT().getName();
        String type = ware.getT().getType();
        Double minprice = ware.getT().getMinOutUnitPrice();
        Double maxprice = ware.getT().getMaxOutUnitPrice();
        if (addr != null && addr.equals("")) {
            jpql.append("and addr like '%" + addr + "%'");
        }
        if (code != null && code.equals("")) {
            jpql.append("and code like '%" + code + "%'");
        }
        if (name != null && name.equals("")) {
            jpql.append("and name like '&" + name + "&'");
        }
        if (type != null && type.equals("")) {
            jpql.append("and type like '%" + type + "%'");
        }
        if (minprice != null && minprice.equals("")) {
            jpql.append("and '" + minprice + "' >= In_unit_price");
        }
        if (maxprice != null && maxprice.equals("")) {
            jpql.append("and In_unit_price >='" + maxprice + "'");
        }
        Query query = entityManager.createQuery(jpql.toString());
        Long count = (Long)query.getSingleResult();
        return count.longValue();

    }
}

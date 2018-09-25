package com.yr.supplier.dao.impl;

import com.yr.entitys.bo.supplierBO.SupplierBo;
import com.yr.entitys.page.Page;
import com.yr.entitys.supplier.Supplier;
import com.yr.supplier.dao.SupplierDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
/**
 *供应商模块数据层实现增删改查
 */
@Repository
public class SupplierDaoImpl implements SupplierDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Supplier supplier) {
    String jpql="insert into supplier(code,name,phoneNumber,addr,rank,createTime,createEmpno)values(?1,?2,?3,?4,?5,?6,?7)";
        Query query = (Query) entityManager.createNativeQuery(jpql).setParameter(1, supplier.getCode())
                .setParameter(2, supplier.getName()).setParameter(3,supplier.getPhoneNumber()).setParameter(4, supplier.getAddr())
                .setParameter(5,supplier.getRank()).setParameter(6,new Date()).setParameter(7,supplier.getCreateEmp());
        query.executeUpdate();


    }

    @Override
    public void delete(Integer id) {
        String jpq= "delete from Supplier d where d.id = ?1";
        Query query = entityManager.createQuery(jpq).setParameter(1, id);
        query.executeUpdate();

    }

    @Override
    public void update(Supplier supplier) {
        String jpql = "update Supplier d set d.name=?1,d.phoneNumber=?2,d.addr=?3,d.rank=?4,d.updateTime=?5,d.updateEmp=?6 where d.id=?7";
        Query query = entityManager.createQuery(jpql).setParameter(1,supplier.getName()).setParameter(2,supplier.getPhoneNumber()).setParameter(3,supplier.getAddr())
                .setParameter(4,supplier.getRank()).setParameter(5,new Date()).setParameter(6,supplier.getUpdateEmp()).setParameter(7,supplier.getId());
        query.executeUpdate();
    }

    @Override
    public Long getCount(Page<SupplierBo>page) {
        String jpql="select count(*) from Supplier d where 1=1";
        String code=page.getT().getCode();
        String name=page.getT().getName();
        String addr=page.getT().getAddr();
        if (code !=null && code !=""){
            jpql +=" and d.code like :code ";
        }if(name != null && name !="" ){
            jpql +=" and d.name like :name ";
        }if (addr != null && addr !=""){

        }

        Query query =entityManager.createQuery(jpql);
        if(code !=null && code !=""){
            query.setParameter("code","%"+code+"%");
        }if(name != null && name !=""){
            query.setParameter("name","%"+name+"%");
        }if(addr !=null && addr !=""){
            query.setParameter("addr","%"+addr+"%");
        }
        Long count =(Long)query.getSingleResult();
        return count;
    }

    @Override
    public Supplier getById(Integer id) {
        String jpql = "SELECT a FROM Supplier a WHERE  a.id = ?1";
        Supplier supplier = (Supplier)entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();

        return supplier;
    }

    @Override
    public List<SupplierBo> query(Page<SupplierBo> page) {
        String jpql="select d from Supplier d where 1=1 ";
        String code=page.getT().getCode();
        String name=page.getT().getName();
        String addr=page.getT().getAddr();
        if (code !=null && code !=""){
            jpql +=" and d.code like :code ";
        }if(name != null && name !="" ){
            jpql +=" and d.name like :name ";
        }if (addr != null && addr !=""){

        }

        Query query =entityManager.createQuery(jpql);
        if(code !=null && code !=""){
            query.setParameter("code","%"+code+"%");
        }if(name != null && name !=""){
            query.setParameter("name","%"+name+"%");
        }if(addr !=null && addr !=""){
            query.setParameter("addr","%"+addr+"%");
        }
        System.out.println(jpql);
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<SupplierBo> list = query.getResultList();
        return list;
    }
}

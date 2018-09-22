package com.yr.depot.dao.impl;

import com.yr.depot.dao.DepotDao;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 *仓库模块数据层实现增删改查
 */
@Repository("depoDaoImpl")
public class DepotDaoImpl implements DepotDao {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 仓库添加
     * @param depot
     */
    @Override
    public void add(Depot depot) {
        entityManager.persist(depot);
    }

    /**
     * 仓库删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Depot depot=entityManager.find(Depot.class,id);
        entityManager.remove(depot);
    }

    /**
     * 仓库修改
     * @param depot
     */
    @Override
    public void update(Depot depot) {
       entityManager.merge(depot);
    }

    /**
     * 查询总条数
     * 返回int类型
     * @param depot
     * @return
     */
    @Override
    public Long getCount(Depotbo depot) {
    String jpql="select count(*) from Depot d where 1=1";
        String code=depot.getCode();
        String name=depot.getName();
        String addr=depot.getAddr();
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

    /**
     * 根据id查询仓库数据
     * 返回对象
     * @param id
     * @return
     */
    @Override
    public Depot getById(Integer id) {
        Depot depot=entityManager.find(Depot.class,id);
        return depot;
    }

    /**
     * 分页查询仓库数据
     * 返回list
     * @param page
     * @return
     */
    @Override
    public List<Depotbo> query(Page<Depotbo> page) {
        String jpql="select d from Depot d where 1=1 ";
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
        List<Depotbo> list = query.getResultList();
        return list;
    }

}
package com.yr.depot.dao.impl;

import com.yr.depot.dao.DepotDao;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 *仓库模块数据层实现增删改查
 */
@Repository("depotDaoImpl")
public class DepotDaoImpl implements DepotDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 分页查询仓库数据
     * 返回list
     * @param page
     * @return
     */
    @Override
    public List<Depotbo> query(Page<Depotbo> page) {
        String jpql="select d from Depot d where 1=1";
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
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<Depotbo> list = query.getResultList();
        return list;
    }

    /**
     * 查询总条数
     * 返回int类型
     * @param page
     * @return
     */
    @Override
    public Long getCount(Page<Depotbo> page) {
        String jpql="select count(*) from Depot d where 1=1";
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


    /**
     * 仓库添加
     * @param depot
     */
    @Override
    public void add(Depot depot) {
        depot.setCreateEmp("元元");
        depot.setCreateTime(new Timestamp(System.currentTimeMillis()));
        depot.setUpdateEmp("元元");
        depot.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        entityManager.persist(depot);
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
     * 删除 和批量删除
     * @param id
     */
    public void delete(Integer [] id){
        List<Integer> list = Arrays.asList(id);//将数组转成list
        String jpql = "delete from Depot u where u.id in(:ids)";
        Query query = entityManager.createQuery(jpql).setParameter("ids",list);
        query.executeUpdate();
    }

    /**
     * 查询仓库 提供给销售调
     * @param name
     * @return
     */
    public List<Depot> getname(){
        String jpql="select d from where Depot d where 1=1";
        List<Depot> list=entityManager.createQuery(jpql).getResultList();
        return  list;
    }
}
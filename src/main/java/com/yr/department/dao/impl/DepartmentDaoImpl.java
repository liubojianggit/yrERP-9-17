package com.yr.department.dao.impl;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void add(Department depart) {
        String sql = "insert department (code,name,sup_code,createTime,createEmpno)values(?1,?2,?3,?4,?5)";
        Query query = (Query) entityManager.createNativeQuery(sql).setParameter(1, depart.getCode())
                .setParameter(2, depart.getName()).setParameter(3, depart.getSupCode()).setParameter(4,new Date()).setParameter(5,depart.getCreateEmpno());
        query.executeUpdate();
    }

    @Override
    public void delete(Integer id) {
        String jpql= "delete from Department d where d.id = ?1";
        Query query = entityManager.createQuery(jpql).setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public void update(Department depart) {
        String jpql = "update Department d set d.name=?1,d.supCode=?2,d.updateTime=?3,d.updateEmpno=?4 where d.id=?5";
        Query query = entityManager.createQuery(jpql).setParameter(1,depart.getName()).setParameter(2,depart.getSupCode())
                .setParameter(3,new Date()).setParameter(4,depart.getUpdateEmpno()).setParameter(5,depart.getId());
        query.executeUpdate();

    }

    @Override
    public Long getCount(Departmentbo departbo) {
        String jpql="select count(*) from Depot d where 1=1";
        String code=departbo.getCode();
        String name=departbo.getName();
        if (code !=null && code !=""){
            jpql +=" and d.code like :code ";
        }if(name != null && name !="" ){
            jpql +=" and d.name like :name ";
        }

        Query query =entityManager.createQuery(jpql);
        if(code !=null && code !=""){
            query.setParameter("code","%"+code+"%");
        }if(name != null && name !=""){
            query.setParameter("name","%"+name+"%");
        }
        Long count =(Long)query.getSingleResult();
        return count;

    }

    @Override
    public Department getById(Integer id) {
        String jpql = "SELECT d FROM Department d WHERE  d.id = ?1";
        Department dpart = (Department) entityManager.createQuery(jpql).setParameter(1, id).getSingleResult();

        return dpart;
    }

    @Override
    public List<Departmentbo> query(Page<Departmentbo> page) {
        String jpql="select d from Department d where 1=1 ";
        String code=page.getT().getCode();
        String name=page.getT().getName();
        if (code !=null && code !=""){
            jpql +=" and d.code like :code ";
        }if(name != null && name !="" ){
            jpql +=" and d.name like :name ";
        }

        Query query =entityManager.createQuery(jpql);
        if(code !=null && code !=""){
            query.setParameter("code","%"+code+"%");
        }if(name != null && name !=""){
            query.setParameter("name","%"+name+"%");
        }
        System.out.println(jpql);
        query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());//查询分页
        List<Departmentbo> list = query.getResultList();
        return  list;
    }

    @Override
    public List<Department> querycod() {
        String  jpql="select code from Department";
       Query query =entityManager.createQuery(jpql);
       List<Department> list=query.getResultList();
       return list;
    }

}

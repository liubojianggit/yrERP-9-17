package com.yr.department.dao.impl;

import com.yr.department.dao.DepartmentDao;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     *查询总条数 并附给搜索框模糊查询
     * @param departbo
     * @return
     */
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

    /**
     * 分页查询所有
     * @param page
     * @return
     */
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

    /**
     * 新增部门
     * @param depart
     */
    @Override
    public void add(Department depart) {
       entityManager.persist(depart);
    }

    /**
     * 根据ID删除部门
     * @param id
     */
    @Override
    public void delete(Integer id) {
      Department department= entityManager.find(Department.class,id);
      entityManager.remove(id);
    }

    /**
     * 修改部门
     * @param depart
     */
    @Override
    public void update(Department depart) {
        entityManager.merge(depart);

    }

    /**
     * 根据ID查询回显部门数据
     * @param id
     * @return
     */
    @Override
    public Department getById(Integer id) {
        Department department=entityManager.find(Department.class,id);
        return department;
    }

    /**
     * 跳转添加页面无数据
     * @return
     * 单独查询部门的编号，以供父级id选择需要返回一个list
     */
    @Override
    public List<Department> querycod() {
        String  jpql="select code from Department";
        Query query =entityManager.createQuery(jpql);
        List<Department> list=query.getResultList();
        return list;
    }

    /**
     * 查询部门编号返回map 提供给用户表
     * @param code
     * @param name
     * @return
     */
    @Override
    public Map<String, Object> querys(String code) {
        String sql="select code from department";
        Query query=entityManager.createQuery(sql);
        Map<String , Object> mao=new HashMap<>();
        mao.put(code,mao);
        return mao;
    }

}

package com.yr.department.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yr.department.dao.DepartmentDao;
import com.yr.entitys.department.Department;
import org.springframework.stereotype.Repository;

/**
 * 部门Dao接口实现类
 */
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext //@PersistenceContext 注解来标记成员变量
    private EntityManager entityManager;

    /**
     * 查询所有数据 返回list
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Department> query (){
        String sql= "select u from department u where 1=1";
        Query query = entityManager.createQuery(sql);
        List<Department>list=query.getResultList();
        return list;
    }

 /*   *//**
     * 查询总条数
     *//*
    @Override
    public Long departmentCount(Page<Departmentbo>page){
        String sql="select count(*) from department where 1=1";
        Query query=entityManager.createQuery(sql);
        Long count = (Long) query.getSingleResult();
        return count;
    }*/

    /**
     * 根据ID查询部门 并回显
     */
    @Override
    public Department departmentId(Integer id) {
        Department department=entityManager.find(Department.class, id);

        return department;
    }

    /**
     * 新增部门
     */
    @Override
    public void add(Department department) {
        entityManager.persist(department);
    }

    /**
     * 删除部门
     */
    @Override
    public void delete(Integer id) {
        Department department=entityManager.find(Department.class, id);
        entityManager.remove(department);
    }

    /**
     * 修改部门
     */
    @Override
    public void update(Department department) {
        entityManager.merge(department);
    }

    /**
     * 查询部门编号,提供给用户调用
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String,Object>querys(){
        String sql="select * from department";
        List<Department> list = entityManager.createQuery(sql).getResultList();
        Map<String,Object>map=new HashMap<>();
        for (Department department : list) {
            map.put( department.getCode(),department.getName() );//编号key 名字为值
        }
        return map;
    }
}

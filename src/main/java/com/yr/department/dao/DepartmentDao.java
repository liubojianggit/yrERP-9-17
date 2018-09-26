package com.yr.department.dao;

import java.util.List;
import java.util.Map;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.department.Department;

/**
 * 部门Dao接口类
 */
public interface DepartmentDao {

   /**
     * 分页查询所有
     * @param page
     * @return
     */
     List<Department>query();

     /*//**
     * 查询总条数
     * @param search
     * @return Integer
     *//*
    Long departmentCount(Page<Departmentbo> page);*/

    /**
     * 根据ID查询部门 并回显
     * @return
     */
    Department departmentId(Integer id);

    /**
     * 新增部门
     * @param department
     */
    void add(Department department);

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改部门
     * @param department
     */
    void update(Department department);

    /**
     * 查询部门编号,提供给用户调用
     * @param code
     * @return
     */
    Map<String,Object>querys();

}

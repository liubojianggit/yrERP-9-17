package com.yr.department.service;

import java.util.Map;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.department.Department;

/**
 *部门Service接口
 */
public interface DepartmentService {

    /**
     * 查询所有
     * @return
     */
    String query();

   /**
     * 根据ID查询部门 并回显
     * @param id
     * @return
     */
    Departmentbo departmentId(Integer id);

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
    Map<String,Object> querys();

    /**
     * 根据部门名字返回部门编号
     * @param depaName
     * @return
     */
    String getDepaCode(String depaName);
}

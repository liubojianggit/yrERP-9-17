package com.yr.department.service;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.page.Page;

import java.util.List;
import java.util.Map;

/**
 *部门Service接口
 */
public interface DepartmentService {

    /**
     * 查询所有
     * @return
     */
    String query();

    String queryDepartmentbo(Page<Departmentbo> page);

    /**
    * 获取部门的list集合
    * */
    List<Departmentbo> getDepartmentList();
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
    void add(Departmentbo departmentbo );

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改部门
     * @param department
     */
    void update(Departmentbo departmentbo);

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

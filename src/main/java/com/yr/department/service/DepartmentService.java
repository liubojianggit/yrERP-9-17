package com.yr.department.service;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    /**
     * 分页搜索查询
     * @param page
     * @return
     */
    Page<Departmentbo> query(Page<Departmentbo> page);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    Department getById(Integer id);

    /**
     * 部门添加
     * @param depart
     */
    void add(Department depart);

    /**
     * 部门修改
     * @param depart
     */

    void update(Department depart);
    /**
     * 部门删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 判断添加部门数据是否为null
     * @param depart
     * @return
     */
    boolean isNullAdd(Department depart);

    /**
     * 判断修改部门数据是否为null
     * @param depart
     * @return
     */
    boolean isNullUpdate(Department depart);
    /**
     * 查询父级id，将父级id以下拉框的形式显示
     */
    List<Department> querycod();

    /**
     * 查询部门 编号和名字 提供给用户表
     * @param code
     * @param name
     * @return
     */
    Map<String, Object> querys(String code);


}

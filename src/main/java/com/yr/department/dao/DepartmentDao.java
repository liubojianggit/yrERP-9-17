package com.yr.department.dao;

import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.depotBo.Depotbo;
import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.page.Page;

import java.util.List;
import java.util.Map;

public interface DepartmentDao {

    /**
     * 为仓库添加数据
     * @param depart
     */
    void add(Department depart);

    /**
     * 根据id删除仓库数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据对象修改仓库数据
     * @param depart
     */
    void update(Department depart);

    /**
     * 查询总数（模糊查询总数）
     * @param departbo
     * @return
     */
    Long getCount(Departmentbo departbo);

    /**
     * 根据id查询仓库数据
     * @param id
     * @return
     */
    Department getById(Integer id);

    /**
     * 分页查询用户
     * @param page
     * @return
     */
    List<Departmentbo> query(Page<Departmentbo> page);

    /**
     * 查询父级id，将父级id以下拉框的形式显示
     */
    List<Department> querycod();

    /**
     * 查询部门编号返回map 提供给用户表
     * @param code
     * @param name
     * @return
     */
    Map<String, Object> querys(String code );

}

package com.yr.department.service.impl;


import com.yr.department.dao.DepartmentDao;
import com.yr.department.service.DepartmentService;
import com.yr.entitys.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {
    @Qualifier("departmentDaoImpl")
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 查询所有部门
     */
    public List<Department>query(){
        List<Department>departments=departmentDao.query();
        return null;
    }
    /**
     * 根据ID查询部门 并回显
     */
    @Override
    public Department departmentId(Integer id) {
        return departmentDao.departmentId(id);
    }

    /**
     * 新增部门
     */
    @Override
    public void add(Department department) {

        departmentDao.add(department);
    }

    /**
     * 删除部门
     */
    @Override
    public void delete(Integer id) {

        departmentDao.delete(id);

    }

    /**
     * 修改部门
     */
    @Override
    public void update(Department department) {

        departmentDao.update(department);

    }

    /**
     * 查询部门编号,提供给用户调用
     * @param code
     * @return
     */
    public Map<String,Object> querys(String code){

        return departmentDao.querys(code);
    }
}
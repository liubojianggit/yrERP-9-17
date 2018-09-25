package com.yr.department.service.impl;


import com.yr.department.dao.DepartmentDao;
import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.bo.menuBO.MenuBO;
import com.yr.entitys.department.Department;
import com.yr.entitys.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门Service实现类
 */
@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {
    @Qualifier("departmentDaoImpl")
    @Autowired
    private DepartmentDao departmentDao;
    /**
     * 查询所有部门
     */
    @Override
    public List<Departmentbo>query(){
        List<Department>list=departmentDao.query();
        List<Departmentbo>listbo=new ArrayList<>();
        for (Department department:list){
            Departmentbo departmentbo=new Departmentbo();
            departmentbo.setDepartment(department);
            listbo.add(departmentbo);
        }
        Department department1=new Department();
        department1.setName("无");
        department1.setPid(0);
        department1.setId(0);
        Departmentbo departmentbo1=new Departmentbo();
        departmentbo1.setDepartment(department1);
        listbo.add(departmentbo1);
        return listbo;
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
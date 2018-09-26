package com.yr.department.service.impl;


import com.yr.department.dao.DepartmentDao;
import com.yr.department.service.DepartmentService;
import com.yr.entitys.bo.departmentBo.Departmentbo;
import com.yr.entitys.department.Department;
import com.yr.util.DateUtils;
import net.sf.json.JSONArray;
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
    public String query(){
        List<Departmentbo> departmentboList= new ArrayList<Departmentbo>();
        for (Department department : departmentDao.query()) {
            //这里new对象，要使用自动注入吗？？
            Departmentbo departmentbo = new Departmentbo();
            departmentbo.setId(department.getId());
            departmentbo.setName(department.getName());
            departmentbo.setCode(department.getCode());
            departmentbo.setSupCode(department.getSupCode());
            departmentbo.setCreateEmp(department.getCreateEmp());
            departmentbo.setCreateTime(DateUtils.dateToStr(department.getCreateTime(),"dd-MMM-yyyy HH:mm:ss:SSS"));
            departmentbo.setUpdateEmp(department.getUpdateEmp());
            departmentbo.setUpdateTime(DateUtils.dateToStr(department.getUpdateTime(),"dd-MMM-yyyy HH:mm:ss:SSS"));
            departmentboList.add(departmentbo);
        }
        String menuJsonStr = JSONArray.fromObject(departmentboList).toString();
        String strJson = "{" +
                "\"msg\": \"\"," +
                "\"code\": 0," +
                "\"data\":"+menuJsonStr+"," +
                "\"count\": 924," +
                "\"is\": true," +
                "\"tip\": \"操作成功！\"" +
                "}";
        System.out.println(strJson);
        return strJson;
    }

    /**
     * 根据ID查询部门 并回显
     */
    @Override
    public Departmentbo departmentId(Integer id) {
        Department department=departmentDao.departmentId(id);
        Departmentbo departmentbo = new Departmentbo();
        departmentbo.setDepartment(department);
        return departmentbo;
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
     * @param
     * @return
     */
    public Map<String,Object> querys(){

        return departmentDao.querys();
    }
}
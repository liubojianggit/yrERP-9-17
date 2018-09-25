package com.yr.entitys.bo.departmentBo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yr.entitys.department.Department;

import java.io.Serializable;

//部门业务拓展类
public class Departmentbo implements Serializable{
    private Department department;//部门
    private Integer pid;//父ID

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String
    toString() {
        return "Departmentbo{" +
                "department=" + department +
                ", pid=" + pid +
                '}';
    }
}

package com.yr.entitys.bo.departmentBo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yr.entitys.department.Department;

import java.io.Serializable;

//部门业务拓展类
public class Departmentbo implements Serializable{
    private Department department;//部门
    private Integer pid;//父ID
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String createTime;//创建时间
    private String createEmp;//创建人
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String updateTime;//修改时间
    private String updateEmp;//修改人

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }
    @Override
    public String toString() {
        return "Departmentbo{" +
                "department=" + department +
                ", pid=" + pid +
                ", createTime='" + createTime + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateEmp='" + updateEmp + '\'' +
                '}';
    }
}

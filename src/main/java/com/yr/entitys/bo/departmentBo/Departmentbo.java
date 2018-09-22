package com.yr.entitys.bo.departmentBo;

import com.yr.entitys.department.Department;

public class Departmentbo {
    private Department department;
    private String code;
    private String name;
    private String supCode;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupCode() {
        return supCode;
    }

    public void setSupCode(String supCode) {
        this.supCode = supCode;
    }

    @Override
    public String toString() {
        return "Departmentbo{" +
                "department=" + department +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", supCode='" + supCode + '\'' +
                '}';
    }
}

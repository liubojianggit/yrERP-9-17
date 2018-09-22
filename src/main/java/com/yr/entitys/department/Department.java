package com.yr.entitys.department;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 部门实体类!
 */
@Entity
@Table(name = "department")//部门表面
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//部门id
    private String  code;//部门编号
    private String name;//部门昵称
    @Column(name = "sup_code",nullable = false)
    private String supCode;//父级部门编号
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;//部门数据创建时间
    private String createEmpno;//部门数据创建人
    //定义时间格式	自动更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;//部门数据修改时间
    private String updateEmpno;//部门数据修改人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateEmpno() {
        return createEmpno;
    }

    public void setCreateEmpno(String createEmpno) {
        this.createEmpno = createEmpno;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateEmpno() {
        return updateEmpno;
    }

    public void setUpdateEmpno(String updateEmpno) {
        this.updateEmpno = updateEmpno;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", supCode='" + supCode + '\'' +
                ", createTime=" + createTime +
                ", createEmpno='" + createEmpno + '\'' +
                ", updateTime=" + updateTime +
                ", updateEmpno='" + updateEmpno + '\'' +
                '}';
    }
}


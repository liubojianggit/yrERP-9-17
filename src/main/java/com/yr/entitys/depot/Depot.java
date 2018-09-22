package com.yr.entitys.depot;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 仓库模块实体类!
 */
@Entity
@Table(name = "depot")
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//仓库id
    @Column(unique = true, nullable = false)//设置为唯一约束，并且是不为null
    private String code;//仓库编号
    @Column(unique = true, nullable = false)//设置为唯一约束，并且是不为null
    private String name;//仓库名称
    private String addr;//仓库存放地址
    @Column(name = "job_num",nullable = false)
    private String jobnum;//仓库负责人工号
    //定义时间格式	自动更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;//仓库数据创建时间
    private String createEmpno;//仓库数据创建人
    //定义时间格式	自动更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;//仓库数据修改时间
    private String updateEmpno;//仓库数据修改人

    public Integer getId() {
        return id;
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAddr() {
        return addr;
    }

    public String getJobnum() {
        return jobnum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setJobnum(String jobnum) {
        this.jobnum = jobnum;
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
        return "Depot{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", jobnum='" + jobnum + '\'' +
                ", createTime=" + createTime +
                ", createEmpno='" + createEmpno + '\'' +
                ", updateTime=" + updateTime +
                ", updateEmpno='" + updateEmpno + '\'' +
                '}';
    }
}

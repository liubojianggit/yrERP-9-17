package com.yr.entitys.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yr.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 用户实体类
 */
@Cacheable(true)
@Entity
@Table(name = "u_user")
public class User extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//编号
    private String photo;//员工照片
    @Column(name="job_num",unique = true, nullable = false)//设置为唯一约束，并且是不为null
    private String jobNum;//工号
    @Column(name="depa_code")
    private String depaCode;//部门编号
    @Column(unique = true, nullable = false)
    private String name;//姓名
    private Integer sex;//性别
    //定义时间格式	自动更新时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Column(columnDefinition = "date")//columnDefinition设置数据库映射类型
    private Date birthday;//生日
    private String addr;//地址
    @Column(nullable = false)
    //@Pattern(Pattern="1[3|4|5|7|8][0-9]\\d{8}")   暂留，这里考虑使用springmvc做效验
    private String phoneNumber;//联系电话
    @Column(nullable = false)
    private Integer states;//状态
    @Column(nullable = false)
    private String password;//密码
    @ManyToMany(mappedBy = "user",cascade = CascadeType.REMOVE)//多对多，放弃本端的维护，使用user维护
    private Set<Role> role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getDepaCode() {
        return depaCode;
    }

    public void setDepaCode(String depaCode) {
        this.depaCode = depaCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStates() {
        return states;
    }

    public void setStates(Integer states) {
        this.states = states;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", jobNum='" + jobNum + '\'' +
                ", depaCode='" + depaCode + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", addr='" + addr + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", states=" + states +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

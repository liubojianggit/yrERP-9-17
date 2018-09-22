package com.yr.entitys.user;

import javax.persistence.*;
import java.util.Set;

/**
 * 权限实体类
 */
@Entity
@Table(name="u_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)//不为null
    private String url;//权限url
    @Column(nullable = false)
    private String name;//权限名字
    @Column(nullable = false)
    private String method;//权限方法
    @Column(nullable = false)
    private Integer supId;//上级id
    @ManyToMany//多对多
    @JoinTable(name="u_role_permission",//中间表的名字
            joinColumns={@JoinColumn(name="rid",referencedColumnName="id")},//name连接字段的名字,该字段对应本实体类的字段(默认是id)
            inverseJoinColumns={@JoinColumn(name="pid",referencedColumnName="id")})//另一个连接字段的名字,对应实体类的字段(默认是id)
    private Set<Role> role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getSupId() {
        return supId;
    }

    public void setSupId(Integer supId) {
        this.supId = supId;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> roles) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", sup_id=" + supId +
                '}';
    }
}

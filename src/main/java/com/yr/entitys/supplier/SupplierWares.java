package com.yr.entitys.supplier;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 供应商品实体类
 */
@Entity
@Table(name = "supp_wares")
public class SupplierWares implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id ;//自动增长
    @Column(nullable = false,unique = true)
    private String code;//供应商品的编号，不能为null，唯一约束
    @Column(nullable = false)
    private String name;//供应商品的名称，不能为null
    @Column(nullable = false)
    private String suppPhoto;//供应商品的头像，不能为null
    @Column(nullable = false)
    private String type;//供应商品的类型，不能为null
    @Column(nullable = false,name = "total_inventory")
    private Long totalInventory;//供应商品的总价格，不能为null
    @Column(nullable = false,name = "unit_price")
    private double unitPrice;//供应商品的单价，不能为null
    @Column(nullable = false)
    private String brand;//供应商品的品牌，不能为null
    @Column(nullable = false)
    private String addr;//供应商品的地址，不能为null
    @Column(nullable = false,columnDefinition = "DATE")
    private String createTime;
    @Column(nullable = false)
    private String createEmp;
    @Column(columnDefinition = "DATE")
    private String updateTime;
    private  String updateEmp;

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

    public String getSuppPhoto() {
        return suppPhoto;
    }

    public void setSuppPhoto(String suppPhoto) {
        this.suppPhoto = suppPhoto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Long totalInventory) {
        this.totalInventory = totalInventory;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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
        return "SupplierWares{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", suppPhoto='" + suppPhoto + '\'' +
                ", type='" + type + '\'' +
                ", totalInventory=" + totalInventory +
                ", unitPrice=" + unitPrice +
                ", brand='" + brand + '\'' +
                ", addr='" + addr + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateEmp='" + updateEmp + '\'' +
                '}';
    }
}
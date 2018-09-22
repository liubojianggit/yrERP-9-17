package com.yr.entitys.depot;

import javax.persistence.*;

/**
 * 商品实体类
 */
@Entity
@Table(name = "wares")
public class Ware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自动增长
    private Integer id;
    @Column(name = "ware_type_code",nullable = false)
    private String wareTypeCode;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false,unique = true)
    private String code;
    @Column(name = "ware_photo",nullable = false)
    private String warePhoto;//商品头像
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String addr;//产地
    @Column(nullable = false)
    private String brand;//品牌
    @Column(name = "In_unit_price",nullable = false)
    private Double inUnitPrice;//采购单价
    @Column(name = "out_unit_price",nullable = false)
    private Double outUnitPrice;//销售单价
    @Column(name = "bar_code")
    private String barCode;//条码
    @Column(name = "total_inventory",nullable = false)
    private Long totalInventory;//库存总量
    @Column(nullable = false,columnDefinition = "DATE")
    private String createTime;
    @Column(nullable = false)
    private String createEmp;
    @Column(nullable = false,columnDefinition = "DATE")
    private String updateTime;
    @Column(nullable = false)
    private  String updateEmp;
    private String remark;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWareTypeCode() {
        return wareTypeCode;
    }

    public void setWareTypeCode(String wareTypeCode) {
        this.wareTypeCode = wareTypeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWarePhoto() {
        return warePhoto;
    }

    public void setWarePhoto(String warePhoto) {
        this.warePhoto = warePhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getInUnitPrice() {
        return inUnitPrice;
    }

    public void setInUnitPrice(Double inUnitPrice) {
        this.inUnitPrice = inUnitPrice;
    }

    public Double getOutUnitPrice() {
        return outUnitPrice;
    }

    public void setOutUnitPrice(Double outUnitPrice) {
        this.outUnitPrice = outUnitPrice;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Long getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Long totalInventory) {
        this.totalInventory = totalInventory;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Ware{" +
                "id=" + id +
                ", wareTypeCode='" + wareTypeCode + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                ", warePhoto='" + warePhoto + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", brand='" + brand + '\'' +
                ", inUnitPrice=" + inUnitPrice +
                ", outUnitPrice=" + outUnitPrice +
                ", barCode='" + barCode + '\'' +
                ", totalInventory=" + totalInventory +
                ", createTime='" + createTime + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateEmp='" + updateEmp + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

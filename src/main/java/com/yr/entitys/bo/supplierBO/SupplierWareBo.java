package com.yr.entitys.bo.supplierBO;

import com.yr.entitys.supplier.supplierWares;

/**
 * 供应商品的实体拓展类
 */
public class SupplierWareBo {
    private supplierWares supplierWare;
    private String name;
    private String type;
    private double minUnitPrice;
    private double maxUnitPrice;
    private String brand;//品牌

    public supplierWares getSupplierWare() {
        return supplierWare;
    }

    public void setSupplierWare(supplierWares supplierWare) {
        this.supplierWare = supplierWare;
    }

    public String getName() {
        return name;
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

    public double getMinUnitPrice() {
        return minUnitPrice;
    }

    public void setMinUnitPrice(double minUnitPrice) {
        this.minUnitPrice = minUnitPrice;
    }

    public double getMaxUnitPrice() {
        return maxUnitPrice;
    }

    public void setMaxUnitPrice(double maxUnitPrice) {
        this.maxUnitPrice = maxUnitPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "SupplierWareBo{" +
                "supplierWare=" + supplierWare +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", minUnitPrice=" + minUnitPrice +
                ", maxUnitPrice=" + maxUnitPrice +
                ", brand='" + brand + '\'' +
                '}';
    }
}

package com.yr.entitys.bo.supplierBO;

import com.yr.entitys.supplier.SupplierWares;

/**
 * 供应商品的实体拓展类
 */
public class SupplierWareBo {
    private SupplierWares supplierWare;
    private String name;//供应商品的名称
    private String type;//供应商品的类型
    private double minUnitPrice;//供应商品的最低单价
    private double maxUnitPrice;//供应商品的最高单价
    private String brand;//品牌

    public SupplierWares getSupplierWare() {
        return supplierWare;
    }

    public void setSupplierWare(SupplierWares supplierWare) {
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

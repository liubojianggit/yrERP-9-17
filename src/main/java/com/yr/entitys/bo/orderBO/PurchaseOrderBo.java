package com.yr.entitys.bo.orderBO;

import com.yr.entitys.department.Department;
import com.yr.entitys.depot.Depot;
import com.yr.entitys.order.PurchaseOrder;
import com.yr.entitys.supplier.Supplier;
import com.yr.entitys.supplier.SupplierWares;
import com.yr.entitys.user.User;

public class PurchaseOrderBo {
    //申请采购
    private PurchaseOrder purchaseOrder;
    private  User user;
    private Department department;
    private Supplier supplier;
    private SupplierWares supplierWares;
    private Depot depot;


    private String  purchaseName;
    private String purchaseType;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setSupplierWares(SupplierWares supplierWares) {
        this.supplierWares = supplierWares;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public Department getDepartment() {
        return department;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public SupplierWares getSupplierWares() {
        return supplierWares;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public String getPurchaseType() {
        return purchaseType;
    }
}

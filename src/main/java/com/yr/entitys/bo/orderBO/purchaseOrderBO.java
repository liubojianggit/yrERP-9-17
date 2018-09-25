package com.yr.entitys.bo.orderBO;

import com.yr.entitys.order.PurchaseOrder;

public class purchaseOrderBO {
    private PurchaseOrder purchaseOrder;
	
    //采购商品名称(用于页面查询)
    private String  purchaseName;
    //采购商品类型(用于页面查询)
    private String purchaseType;

    public void setRequisition(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public void setPurchaseName(String purchaseName) {
        this.purchaseName = purchaseName;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public PurchaseOrder getRequisition() {
        return purchaseOrder;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public String getPurchaseType() {
        return purchaseType;
    }
}

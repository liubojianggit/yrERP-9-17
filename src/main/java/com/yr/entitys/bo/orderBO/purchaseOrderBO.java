package com.yr.entitys.bo.orderBO;

import com.yr.entitys.order.PurchaseOrder;

/**
 * 采购订单
 * @author
 * @since
 */
public class PurchaseOrderBO {
    private PurchaseOrder purchaseOrder;
	
    //采购商品名称(用于页面查询)
    private String  purchaseName;
    //采购商品类型(用于页面查询)
    private String purchaseType;

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

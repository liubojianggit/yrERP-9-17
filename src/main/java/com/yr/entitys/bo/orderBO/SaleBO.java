package com.yr.entitys.bo.orderBO;

import com.yr.entitys.order.SaleOrder;

import java.io.Serializable;

public class SaleBO implements Serializable{//销售的业务操作
    private SaleOrder saleOrder;

    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }
    //销售订单编号
   /* private String code;
    //购买客户
    private String customerBuy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerBuy() {
        return customerBuy;
    }

    public void setCustomerBuy(String customerBuy) {
        this.customerBuy = customerBuy;
    }*/

}
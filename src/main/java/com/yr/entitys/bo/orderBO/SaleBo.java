package com.yr.entitys.bo.orderBO;

import com.yr.entitys.order.Sale;

import java.io.Serializable;

public class SaleBo implements Serializable{//销售的业务操作
    private Sale sale;
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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
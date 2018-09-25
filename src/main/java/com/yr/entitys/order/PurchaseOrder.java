package com.yr.entitys.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yr.common.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaseOrder")
public class PurchaseOrder extends BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //采购订单编号
    private String code;

    //采购人工号
    @Column(name = "job_num")
    private String jobNumber;

    //采购部门编号
    @Column(name = "depa_code")
    private String departmentCode;

    //审批人
    private String approver;

    //采购商品名称
    @Column(name = "purc_ware_name")
    private String purchasName;

    //采购商品类型
    @Column(name = "purc_ware_type")
    private String purchaseType;

    //采购商品数量
    @Column(name = "purc_ware_num")
    private Long purchaseNumber;

    //供应商编号
    @Column(name = "supp_code")
    private String supplierCode;

    //单价

    @Column(name = "unit_price")
    private Double unitPrice;

    //总价
    @Column(name = "total_price")
    private  Double totalPrice;

    //采购单状态(0驳回，1交易成功，2待审核，3申请退货，4退货成功)
    private  Integer status;

    //收货人
    private  String consignee;

    //收货仓库id;
    @Column(name = "depot_code")
    private String  depotCode;

   /* @JsonFormat(pattern ="yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;

    private String createEmp;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private String updateEmp;*/


    public void setPurchasName(String purchasName) {
        this.purchasName = purchasName;
    }

  /*  public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }*/

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public void setPurchaseNumber(Long purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode;
    }

    public String getPurchasName() {
        return purchasName;
    }

   /* public Date getCreateTime() {
        return createTime;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }
*/
    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getApprover() {
        return approver;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public Long getPurchaseNumber() {
        return purchaseNumber;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getDepotCode() {
        return depotCode;
    }

    public PurchaseOrder() {

    }

    public PurchaseOrder(Integer id, String code, String jobNumber, String departmentCode, String approver, String purchasName, String purchaseType, Long purchaseNumber, String supplierCode, Double unitPrice, Double totalPrice, Integer status, String consignee, String depotCode) {
        this.id = id;
        this.code = code;
        this.jobNumber = jobNumber;
        this.departmentCode = departmentCode;
        this.approver = approver;
        this.purchasName = purchasName;
        this.purchaseType = purchaseType;
        this.purchaseNumber = purchaseNumber;
        this.supplierCode = supplierCode;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.status = status;
        this.consignee = consignee;
        this.depotCode = depotCode;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                ", approver='" + approver + '\'' +
                ", purchasName='" + purchasName + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", purchaseNumber=" + purchaseNumber +
                ", supplierCode='" + supplierCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", consignee='" + consignee + '\'' +
                ", depotCode='" + depotCode + '\'' +
                '}';
    }
}

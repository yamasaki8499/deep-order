package com.deepbar.entity;

import com.deepbar.framework.jsonhelp.DateTimeJsonSerializer;
import com.deepbar.framework.model.Column;
import com.deepbar.framework.model.Identity;
import com.deepbar.framework.model.PrimaryKey;
import com.deepbar.framework.model.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "LIGHT_SEQ")
public class LightSeq implements Serializable{
    private Long id;
    private String alias;
    private boolean checkedBill;
    private Date openTime;
    private String openUser;
    private Date settleTime;
    private String settleUser;
    private boolean fullyPaid;
    private BigDecimal paidAmount;
    private BigDecimal billPrice;
    private BigDecimal finalPrice;
    private String paymentType;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ALIAS")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Column(name = "CHECKED_BILL")
    public boolean isCheckedBill() {
        return checkedBill;
    }

    public void setCheckedBill(boolean checkedBill) {
        this.checkedBill = checkedBill;
    }

    @Column(name = "OPEN_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    @Column(name = "OPEN_USER")
    public String getOpenUser() {
        return openUser;
    }

    public void setOpenUser(String openUser) {
        this.openUser = openUser;
    }

    @Column(name = "SETTLE_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    @Column(name = "SETTLE_USER")
    public String getSettleUser() {
        return settleUser;
    }

    public void setSettleUser(String settleUser) {
        this.settleUser = settleUser;
    }

    @Column(name = "HAS_FULLY_PAID")
    public boolean hasFullyPaid() {
        return fullyPaid;
    }

    public void setFullyPaid(boolean fullyPaid) {
        this.fullyPaid = fullyPaid;
    }

    @Column(name = "PAID_AMOUNT")
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Column(name = "BILL_PRICE")
    public BigDecimal getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(BigDecimal billPrice) {
        this.billPrice = billPrice;
    }

    @Column(name = "FINAL_PRICE")
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Column(name = "PAYMENT_TYPE")
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "LightSeq{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", checkedBill=" + checkedBill +
                ", openTime='" + openTime + '\'' +
                ", openUser='" + openUser + '\'' +
                ", settleTime='" + settleTime + '\'' +
                ", settleUser='" + settleUser + '\'' +
                ", fullyPaid=" + fullyPaid +
                ", paidAmount=" + paidAmount +
                ", billPrice=" + billPrice +
                ", finalPrice=" + finalPrice +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}

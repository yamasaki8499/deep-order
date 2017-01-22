package com.deepbar.entity;

import com.deepbar.framework.jsonhelp.DateTimeJsonSerializer;
import com.deepbar.framework.model.Column;
import com.deepbar.framework.model.Identity;
import com.deepbar.framework.model.PrimaryKey;
import com.deepbar.framework.model.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "RECHARGE_RECORD")
public class RechargeRecord implements Serializable {
    private Long id;
    private Long catalogId;
    private Integer amount;
    private String rechargeUser;
    private Date rechargeTime;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CATALOG_ID")
    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Column(name = "RECHARGE_USER")
    public String getRechargeUser() {
        return rechargeUser;
    }

    public void setRechargeUser(String rechargeUser) {
        this.rechargeUser = rechargeUser;
    }

    @Column(name = "RECHARGE_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    @Override
    public String toString() {
        return "RechargeRecord{" +
                "id=" + id +
                ", catalogId=" + catalogId +
                ", amount=" + amount +
                ", rechargeUser='" + rechargeUser + '\'' +
                ", rechargeTime='" + rechargeTime + '\'' +
                '}';
    }
}

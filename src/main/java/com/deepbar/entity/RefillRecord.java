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
@Table(scheme = "REFILL_RECORD")
public class RefillRecord implements Serializable{
    private Long id;
    private Long catalogId;
    private String refillUser;
    private Date refillTime;
    private String Amount;

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

    @Column(name = "REFILL_USER")
    public String getRefillUser() {
        return refillUser;
    }

    public void setRefillUser(String refillUser) {
        this.refillUser = refillUser;
    }

    @Column(name = "REFILL_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getRefillTime() {
        return refillTime;
    }

    public void setRefillTime(Date refillTime) {
        this.refillTime = refillTime;
    }

    @Column(name = "AMOUNT")
    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "RefillRecord{" +
                "id=" + id +
                ", catalogId=" + catalogId +
                ", refillUser='" + refillUser + '\'' +
                ", refillTime='" + refillTime + '\'' +
                ", Amount='" + Amount + '\'' +
                '}';
    }
}

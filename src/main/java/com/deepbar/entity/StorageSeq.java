package com.deepbar.entity;

import com.deeporder.framework.jsonhelp.DateTimeJsonSerializer;
import com.deeporder.framework.model.Column;
import com.deeporder.framework.model.Identity;
import com.deeporder.framework.model.PrimaryKey;
import com.deeporder.framework.model.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "STORAGE_SEQ")
public class StorageSeq implements Serializable {

    private Long id;
    private String mobile;
    private String tagNumber;
    private String storeUser;
    private Date storeTime;
    private String claimUser;
    private Date claimTime;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "TAG_NUMBER")
    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    @Column(name = "STORE_USER")
    public String getStoreUser() {
        return storeUser;
    }

    public void setStoreUser(String storeUser) {
        this.storeUser = storeUser;
    }

    @Column(name = "STORE_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    @Column(name = "CLAIM_USER")
    public String getClaimUser() {
        return claimUser;
    }

    public void setClaimUser(String claimUser) {
        this.claimUser = claimUser;
    }

    @Column(name = "CLAIM_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(Date claimTime) {
        this.claimTime = claimTime;
    }

    @Override
    public String toString() {
        return "StorageSeq{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", tagNumber='" + tagNumber + '\'' +
                ", storeUser='" + storeUser + '\'' +
                ", storeTime='" + storeTime + '\'' +
                ", claimUser='" + claimUser + '\'' +
                ", claimTime='" + claimTime + '\'' +
                '}';
    }
}

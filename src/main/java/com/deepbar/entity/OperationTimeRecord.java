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
@Table(scheme = "OPERATION_TIME_RECORD")
public class OperationTimeRecord implements Serializable {

    private Long id;
    private Date operationDate;
    private Date signInTime;
    private String signInUser;
    private Date signOutTime;
    private String signOutUser;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "OPERATION_DATE")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    @Column(name = "SIGN_IN_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    @Column(name = "SIGN_IN_USER")
    public String getSignInUser() {
        return signInUser;
    }

    public void setSignInUser(String signInUser) {
        this.signInUser = signInUser;
    }

    @Column(name = "SIGN_OUT_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Date signOutTime) {
        this.signOutTime = signOutTime;
    }

    @Column(name = "SIGN_OUT_USER")
    public String getSignOutUser() {
        return signOutUser;
    }

    public void setSignOutUser(String signOutUser) {
        this.signOutUser = signOutUser;
    }

    @Override
    public String toString() {
        return "OperationTimeRecord{" +
                "id=" + id +
                ", operationDate='" + operationDate + '\'' +
                ", signInTime='" + signInTime + '\'' +
                ", signInUser='" + signInUser + '\'' +
                ", signOutTime='" + signOutTime + '\'' +
                ", signOutUser='" + signOutUser + '\'' +
                '}';
    }
}

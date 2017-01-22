package com.deepbar.framework.model;

import com.deepbar.framework.jsonhelp.DateTimeJsonDeserializer;
import com.deepbar.framework.jsonhelp.DateTimeJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by josh on 15/6/17.
 */
public abstract class BaseModel implements Serializable {

    private boolean deleted;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;

    @Column(name = "DELETED")
    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "CREATE_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    @JsonDeserialize(using = DateTimeJsonDeserializer.class)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "CREATE_USER")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Column(name = "UPDATE_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }

    @JsonDeserialize(using = DateTimeJsonDeserializer.class)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "UPDATE_USER")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}

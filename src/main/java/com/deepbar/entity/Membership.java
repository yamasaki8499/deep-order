package com.deepbar.entity;

import com.deepbar.framework.model.*;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "MEMBERSHIP")
public class Membership extends BaseModel implements Serializable{
    private Long id;
    private String loginName;
    private String mobile;
    private String wechat;
    private String password;
    private Integer vipLevelId;
    private String gender;
    private String lastName;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LOGIN_NAME")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "WECHAT")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "VIP_LEVEL_ID")
    public Integer getVipLevelId() {
        return vipLevelId;
    }

    public void setVipLevelId(Integer vipLevelId) {
        this.vipLevelId = vipLevelId;
    }

    @Column(name = "GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                ", password='" + password + '\'' +
                ", vipLevelId=" + vipLevelId +
                ", gender='" + gender + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

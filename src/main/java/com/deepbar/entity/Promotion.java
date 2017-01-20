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
@Table(scheme = "PROMOTION")
public class Promotion implements Serializable {
    private Long id;
    private boolean main;
    private String url;
    private String description;
    private String name;
    private Date beginDate;
    private Date endDate;
    private String address;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "MAIN")
    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "BEGIN_DATE")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @Column(name = "END_DATE")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", main=" + main +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

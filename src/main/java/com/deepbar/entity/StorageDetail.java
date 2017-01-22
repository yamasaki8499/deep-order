package com.deepbar.entity;

import com.deepbar.framework.model.Column;
import com.deepbar.framework.model.Identity;
import com.deepbar.framework.model.PrimaryKey;
import com.deepbar.framework.model.Table;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "STORAGE_DETAIL")
public class StorageDetail implements Serializable {
    private Long id;
    private Long catalogId;
    private Integer amount;
    private String description;
    private String tagNumber;

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

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "TAG_NUMBER")
    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    @Override
    public String toString() {
        return "StorageDetail{" +
                "id=" + id +
                ", catalogId=" + catalogId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", tagNumber='" + tagNumber + '\'' +
                '}';
    }
}

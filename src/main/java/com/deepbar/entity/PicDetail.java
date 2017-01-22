package com.deepbar.entity;

import com.deepbar.framework.model.*;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "PIC_DETAIL")
public class PicDetail extends BaseModel implements Serializable {
    private Long id;
    private String url;
    private Long catalogId;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "CATALOG_ID")
    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public String toString() {
        return "PicDetail{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", catalogId=" + catalogId +
                '}';
    }
}

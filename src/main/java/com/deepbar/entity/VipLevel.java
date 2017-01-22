package com.deepbar.entity;

import com.deepbar.framework.model.*;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "VIP_LEVEL")
public class VipLevel extends BaseModel implements Serializable {
    private Long id;
    private String level;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LEVEL")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "VipLevel{" +
                "id=" + id +
                ", level='" + level + '\'' +
                '}';
    }
}

package com.deepbar.entity;

import com.deeporder.framework.model.*;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "PAY_BY_PAY_FOR")
public class PayByPayFor extends BaseModel implements Serializable {

    private Long id;
    private Long payByLightSeq;
    private Long payForLightSeq;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "PAY_BY_LIGHT_SEQ")
    public Long getPayByLightSeq() {
        return payByLightSeq;
    }

    public void setPayByLightSeq(Long payByLightSeq) {
        this.payByLightSeq = payByLightSeq;
    }

    @Column(name = "PAY_FOR_LIGHT_SEQ")
    public Long getPayForLightSeq() {
        return payForLightSeq;
    }

    public void setPayForLightSeq(Long payForLightSeq) {
        this.payForLightSeq = payForLightSeq;
    }

    @Override
    public String toString() {
        return "PayByPayFor{" +
                "id=" + id +
                ", payByLightSeq=" + payByLightSeq +
                ", payForLightSeq=" + payForLightSeq +
                '}';
    }
}

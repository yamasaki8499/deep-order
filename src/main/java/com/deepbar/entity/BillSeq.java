package com.deepbar.entity;

import com.deeporder.framework.jsonhelp.DateTimeJsonSerializer;
import com.deeporder.framework.model.Column;
import com.deeporder.framework.model.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "BILL_SEQ")
public class BillSeq implements Serializable {
    private Long id;
    private Long lightSeqId;
    private String orderUser;
    private Date orderTime;
    private BigDecimal price;

    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "LIGHT_SEQ_ID")
    public Long getLightSeqId() {
        return lightSeqId;
    }

    public void setLightSeqId(Long lightSeqId) {
        this.lightSeqId = lightSeqId;
    }

    @Column(name = "ORDER_USER")
    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    @Column(name = "ORDER_TIME")
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillSeq{" +
                "id=" + id +
                ", lightSeqId=" + lightSeqId +
                ", orderUser='" + orderUser + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", price=" + price +
                '}';
    }
}

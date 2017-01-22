package com.deepbar.entity;

import com.deepbar.framework.model.Column;
import com.deepbar.framework.model.Table;

import java.io.Serializable;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "BILL_DETAIL")
public class BillDetail implements Serializable {
    private Long billSeqId;
    private Long catalogId;
    private Integer amount;

    @Column(name = "BILL_SEQ_ID")
    public Long getBillSeqId() {
        return billSeqId;
    }

    public void setBillSeqId(Long billSeqId) {
        this.billSeqId = billSeqId;
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

    @Override
    public String toString() {
        return "BillDetail{" +
                "billSeqId=" + billSeqId +
                ", catalogId=" + catalogId +
                ", amount=" + amount +
                '}';
    }
}

package com.deepbar.entity;

import com.deepbar.framework.model.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by RayLiu on 2016/10/26.
 */
@Table(scheme = "CATALOG")
public class Catalog extends BaseModel implements Serializable{
    private Long id;
    private String name;
    private String madeIn;
    private String brand;
    private String introduction;
    private String flavor;
    private BigDecimal price;
    private BigDecimal cost;
    private boolean recommendation;
    private boolean bottle;
    private Integer balanceAmount;
    private String iconUrl;
    private boolean onSale;

    @PrimaryKey
    @Identity
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "MADE_IN")
    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    @Column(name = "BRAND")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "INTRODUCTION")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column(name = "FLAVOR")
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "COST")
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Column(name = "IS_RECOMMENDATION")
    public boolean isRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    @Column(name = "IS_BOTTLE")
    public boolean isBottle() {
        return bottle;
    }

    public void setBottle(boolean bottle) {
        this.bottle = bottle;
    }

    @Column(name = "BALANCE_AMOUNT")
    public Integer getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Integer balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    @Column(name = "ICON_URL")
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Column(name = "IS_ON_SALE")
    public boolean isOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", madeIn='" + madeIn + '\'' +
                ", brand='" + brand + '\'' +
                ", introduction='" + introduction + '\'' +
                ", flavor='" + flavor + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", recommendation=" + recommendation +
                ", bottle=" + bottle +
                ", balanceAmount=" + balanceAmount +
                ", iconUrl='" + iconUrl + '\'' +
                ", onSale=" + onSale +
                '}';
    }
}

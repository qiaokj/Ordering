package com.example.order.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 钱包 PO 类 一个是业务功能，比如，提供用户查询交易流水信息
 */
@Entity
public class WalletInfo {

    /**
     * 钱包交易流水号
     */
    @Id
    private String tradeNo;

    private Date tradeTime;

    private BigDecimal tradeAmount;

    private Integer tradeType;

    private String virtualWalletInId;

    private String virtualWalletOutId;

    private String virtualWalletTradeNo;

    private Date insertTime;

    private Date updateTime;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getVirtualWalletInId() {
        return virtualWalletInId;
    }

    public void setVirtualWalletInId(String virtualWalletInId) {
        this.virtualWalletInId = virtualWalletInId;
    }

    public String getVirtualWalletOutId() {
        return virtualWalletOutId;
    }

    public void setVirtualWalletOutId(String virtualWalletOutId) {
        this.virtualWalletOutId = virtualWalletOutId;
    }

    public String getVirtualWalletTradeNo() {
        return virtualWalletTradeNo;
    }

    public void setVirtualWalletTradeNo(String virtualWalletTradeNo) {
        this.virtualWalletTradeNo = virtualWalletTradeNo;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

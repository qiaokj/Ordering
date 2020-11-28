package com.example.order.po;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 虚拟钱包  非业务功能，保证数据的一致性。虚拟钱包系统不应该感知具体的业务交易类型。
 * 虚拟钱包支持的操作，仅仅是余额的加加减减操作，不涉及复杂业务概念，职责单一、功能通用。
 * 不应该在虚拟钱包系统的交易流水中记录交易类型
 */
@Entity
@DynamicUpdate
public class VirtualWallet implements Serializable {

    /**
     * 虚拟钱包交易单号
     */
    @Id
    private String tradeNo;

    /**
     * 交易时间
     */
    private Date tradeTime;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 交易类型
     */
    private Integer tradeType;

    /**
     * 虚拟钱包交易流水
     */
    private String virtualWalletId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 钱包交易流水号
     */
    private String walletTradeNo;

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

    public String getVirtualWalletId() {
        return virtualWalletId;
    }

    public void setVirtualWalletId(String virtualWalletId) {
        this.virtualWalletId = virtualWalletId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWalletTradeNo() {
        return walletTradeNo;
    }

    public void setWalletTradeNo(String walletTradeNo) {
        this.walletTradeNo = walletTradeNo;
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

package com.example.order.po;

import com.example.order.common.util.provider.OrderConstantProvider;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "order_info")
@DynamicUpdate
public class OrderMaster {

     /**
      * 订单 ID
      */
     @Id
     private String id;

     /**
      * 订单支付类型
      */
     private Integer payType;

     /**
      * 订单支付状态, 默认未支付
      */
     private Integer payStatus = OrderConstantProvider.PayStatus.WAIT_PAYMENT.getCode();

     /**
      * 订单状态
      */
     private Integer orderStatus = OrderConstantProvider.OrderStatus.WAITING_PAY.getStatus();

     /**
      * 订单支付金额
      */
     private BigDecimal amount;

     /**
      * 订单时间
      */
     private Date orderTime;

     /**
      * 用户名称
      */
     private String customerName;

     /**
      * 用户 ID
      */
     private String customerId;

     /**
      * 商家 ID
      */
     private String businessId;

     /**
      * 商家名称
      */
     private String businessName;

     private Date insertTime;
     private Date updateTime;

     /**
      * 是否有效
      */
     private Integer valid = OrderConstantProvider.ORDER_VALID;

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public Integer getPayType() {
          return payType;
     }

     public void setPayType(Integer payType) {
          this.payType = payType;
     }

     public Integer getPayStatus() {
          return payStatus;
     }

     public void setPayStatus(Integer payStatus) {
          this.payStatus = payStatus;
     }

     public BigDecimal getAmount() {
          return amount;
     }

     public void setAmount(BigDecimal amount) {
          this.amount = amount;
     }

     public Date getOrderTime() {
          return orderTime;
     }

     public void setOrderTime(Date orderTime) {
          this.orderTime = orderTime;
     }

     public String getCustomerName() {
          return customerName;
     }

     public void setCustomerName(String customerName) {
          this.customerName = customerName;
     }

     public String getCustomerId() {
          return customerId;
     }

     public void setCustomerId(String customerId) {
          this.customerId = customerId;
     }

     public String getBusinessId() {
          return businessId;
     }

     public void setBusinessId(String businessId) {
          this.businessId = businessId;
     }

     public String getBusinessName() {
          return businessName;
     }

     public void setBusinessName(String businessName) {
          this.businessName = businessName;
     }

     public Integer getValid() {
          return valid;
     }

     public void setValid(Integer valid) {
          this.valid = valid;
     }

     public Integer getOrderStatus() {
          return orderStatus;
     }

     public void setOrderStatus(Integer orderStatus) {
          this.orderStatus = orderStatus;
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

     @Override
     public String toString() {
          return "OrderMaster{" +
                  "id='" + id + '\'' +
                  ", payType=" + payType +
                  ", payStatus=" + payStatus +
                  ", orderStatus=" + orderStatus +
                  ", amount=" + amount +
                  ", orderTime=" + orderTime +
                  ", customerName='" + customerName + '\'' +
                  ", customerId='" + customerId + '\'' +
                  ", businessId='" + businessId + '\'' +
                  ", businessName='" + businessName + '\'' +
                  ", insertTime=" + insertTime +
                  ", updateTime=" + updateTime +
                  ", valid=" + valid +
                  '}';
     }
}

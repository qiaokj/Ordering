package com.example.order.dto;

import java.io.Serializable;

/**
 * @author Qiao
 */
public class CartDto implements Serializable {

    private String productId;

    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

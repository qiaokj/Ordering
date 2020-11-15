package com.example.order.vo;

import java.util.List;

public class ProductCategoryVo<T> implements java.io.Serializable {

    private String name;

    private Integer type;

    private List<T> productList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }
}

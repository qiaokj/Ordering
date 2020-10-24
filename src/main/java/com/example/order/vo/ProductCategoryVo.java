package com.example.order.vo;

import java.util.List;

public class ProductCategoryVo<T> implements java.io.Serializable {

    private String categoryName;

    private Integer categoryId;

    private List<T> productList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<T> getProductList() {
        return productList;
    }

    public void setProductList(List<T> productList) {
        this.productList = productList;
    }
}

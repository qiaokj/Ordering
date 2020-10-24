package com.example.order.service;

import com.example.order.po.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findAllByCategoryType(List<Integer> categpryTypes);

    void save(ProductCategory productCategory);
}

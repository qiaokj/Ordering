package com.example.order.service.impl;

import com.example.order.dao.ProductCategoryRepository;
import com.example.order.po.ProductCategory;
import com.example.order.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findAllByCategoryType(List<Integer> categpryTypes) {
        return repository.findByTypeIn(categpryTypes);
    }

    @Override
    public void save(ProductCategory productCategory) {
        repository.save(productCategory);
    }
}

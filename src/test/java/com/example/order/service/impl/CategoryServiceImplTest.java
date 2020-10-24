package com.example.order.service.impl;

import com.example.order.po.ProductCategory;
import com.example.order.service.CategoryService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
        ProductCategory one = categoryService.findOne(1);
        System.out.println(one);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        System.out.println(all);
    }

    @Test
    public void findAllByCategoryType() {
        List<ProductCategory> allByCategoryType = categoryService.findAllByCategoryType(Lists.newArrayList(2));
        System.out.println(allByCategoryType);

    }

    @Test
    public void save() {
    }
}
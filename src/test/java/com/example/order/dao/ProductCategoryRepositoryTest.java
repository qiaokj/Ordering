package com.example.order.dao;

import com.example.order.po.ProductCategory;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void testFindOne() {
        ProductCategory productCategory = repository.findOne(2);
        System.out.println(productCategory);
    }

    @Test
    public void testUpdateOne() {
        ProductCategory category = new ProductCategory();
        category.setName("烤鱼");
        category.setType(1);
        category.setId(1);
        repository.save(category);
    }

    @Test
    public void addProducCategory() {
        ProductCategory category = new ProductCategory();
        category.setName("面食");//川湘菜
        category.setType(4);
        repository.save(category);
    }


    @Test
    public void deleteProductCategory() {
        repository.delete(2);
    }
}
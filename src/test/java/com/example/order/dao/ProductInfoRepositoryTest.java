package com.example.order.dao;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.po.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void save() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(OddNumberGenerator.generateProductId());
        productInfo.setName("羊杂汤");
        productInfo.setBusinessId("B2020N6759");
        productInfo.setCategory(3);
        productInfo.setPrice(new BigDecimal("12.50"));
        productInfo.setStock(40);
        productInfo.setIcon("");
        productInfo.setStatue(ConstantProvider.ProductStatue.ONSALE.getStatue());

        repository.save(productInfo);
    }


    @Test
    public void findAllByStatueAndBusinessId() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<ProductInfo> allByStatue = repository.findAllByStatue(ConstantProvider.ProductStatue.ONSALE.getStatue(), pageRequest);
        System.out.println(allByStatue.getTotalPages());
        System.out.println(allByStatue.getTotalElements());
        System.out.println(allByStatue.getContent());
    }

    /*@Test
    public void testFindAllByStatue() {
    }

    @Test
    public void testFindAllByStatueAndBusinessId() {
        List<ProductInfo> b2020N0221 = repository.findAllByStatueAndBusinessId(ConstantProvider.ProductStatue.ONSALE.getStatue(), "B2020N0221");
        System.out.println(b2020N0221);
    }

    @Test
    public void findAllByStatueAndCategory() {
        List<ProductInfo> allByStatueAndCategory = repository.findAllByStatueAndCategory(ConstantProvider.ProductStatue.ONSALE.getStatue(), 3);
        System.out.println(allByStatueAndCategory);
    }

    @Test
    public void findAllByStatueAndCategoryAndBusinessId() {
        List<ProductInfo> b2020N0221 = repository.findAllByStatueAndCategoryAndBusinessId(ConstantProvider.ProductStatue.ONSALE.getStatue(), 3, "B2020N0221");
        System.out.println(b2020N0221);
    }*/
}
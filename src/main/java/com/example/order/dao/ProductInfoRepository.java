package com.example.order.dao;

import com.example.order.po.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    public Page<ProductInfo> findAll(Pageable pageable);

    public Page<ProductInfo> findAllByStatue(Integer statue, Pageable pageable);

    public Page<ProductInfo> findAllByStatueAndBusinessId(Integer statue, String businessId, Pageable pageable);

    public Page<ProductInfo> findAllByStatueAndCategory(Integer statue, Integer category, Pageable pageable);

    public Page<ProductInfo> findAllByStatueAndCategoryAndBusinessId(Integer statue, Integer category, String businessId, Pageable pageable);
}

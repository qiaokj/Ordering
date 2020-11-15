package com.example.order.dao;

import com.example.order.po.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    public Page<ProductInfo> findAll(Pageable pageable);

    public Page<ProductInfo> findAllByCategory(Integer categoryId, Pageable pageable);

    public Page<ProductInfo> findAllByStatue(Integer statue, Pageable pageable);

    public Page<ProductInfo> findAllByStatueAndBusinessId(Integer statue, String businessId, Pageable pageable);

    public Page<ProductInfo> findAllByStatueAndCategory(Integer statue, Integer category, Pageable pageable);

    /**
     * 查询指定状态的所有商品
     * @param statue
     * @param category
     * @return
     */
    public List<ProductInfo> findAllByStatueAndCategory(Integer statue, Integer category);

    public Page<ProductInfo> findAllByStatueAndCategoryAndBusinessId(Integer statue, Integer category, String businessId, Pageable pageable);

    public List<ProductInfo> findAllByCategoryIn(List<Integer> types);
}

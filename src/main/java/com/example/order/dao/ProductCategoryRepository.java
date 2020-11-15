package com.example.order.dao;

import com.example.order.po.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 查找所有的类型, 按照分页返回数据
     * @param pageable
     * @return
     */
    Page<ProductCategory> findAll(Pageable pageable);

    /**
     * 查找指定类型下面的类别信息, 按照分页返回数据
     * @param categpryTypes
     * @return
     */
    List<ProductCategory> findByTypeIn(List<Integer> categpryTypes);
}

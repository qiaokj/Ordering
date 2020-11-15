package com.example.order.service.category;

import com.example.order.po.ProductCategory;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    PageVo<ProductCategoryVo<ProductVo>> findAll(Integer pageNo, Integer pageSize);

    PageVo<ProductCategoryVo<ProductVo>> findAll();

    /**
     * 根据商品类型查询
     * @param categpryTypes
     * @return
     */
    PageVo<ProductCategoryVo<ProductVo>> findAllByCategoryType(List<Integer> categpryTypes);

    void save(ProductCategory productCategory);
}

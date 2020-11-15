package com.example.order.service.category.util;

import com.example.order.po.ProductCategory;
import com.example.order.po.ProductInfo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryUtil {

    /**
     * 将商品类别PO类拷贝为VO类
     * @param productCategory
     * @return
     */
    public static ProductCategoryVo<ProductVo> generateProductCategoryVoFromPo(ProductCategory productCategory) {

        ProductCategoryVo<ProductVo> productCategoryVo = new ProductCategoryVo<>();
        BeanUtils.copyProperties(productCategory, productCategoryVo);
        return productCategoryVo;
    }

    /**
     * 将商品类别PO列表转为VO列表
     * @param productCategoryList
     * @param productCategoryVoList
     */
    public static void copyFromPoToProductCategoryVo(List<ProductCategory> productCategoryList, List<ProductCategoryVo<ProductVo>> productCategoryVoList) {

        // 对源对象进行校验
        if (productCategoryList == null || productCategoryList.isEmpty()) {
            return;
        }

        // 对目标对象进行校验
        if (productCategoryVoList == null) {
            productCategoryVoList = new ArrayList<>();
        } else if (!productCategoryVoList.isEmpty()) {
            productCategoryVoList.clear();
        }

        // 对数据进行属性封装拷贝
        for (ProductCategory category : productCategoryList) {
            productCategoryVoList.add(generateProductCategoryVoFromPo(category));
        }
    }
}

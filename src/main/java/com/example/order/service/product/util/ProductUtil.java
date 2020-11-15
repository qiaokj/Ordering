package com.example.order.service.product.util;

import com.example.order.po.ProductInfo;
import com.example.order.vo.ProductVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductUtil {

    /**
     * 将 PO 对象转换成 VO 对象
     * @param productInfo
     * @return
     */
    public static ProductVo generateProductVoFromPo(ProductInfo productInfo) {

        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(productInfo, productVo);
        return productVo;
    }

    /**
     * 根据 PO 类列表转换成 VO 类列表
     * @param productInfoList
     * @param productVoList
     */
    public static void copyFromPoToProductVo(List<ProductInfo> productInfoList, List<ProductVo> productVoList) {

        // 对源对象进行校验
        if (productInfoList == null || productInfoList.isEmpty()) {
            return;
        }

        // 对目标对象进行校验
        if (productVoList == null) {
            productVoList = new ArrayList<>();
        } else if (!productVoList.isEmpty()) {
            productVoList.clear();
        }

        // 对数据进行属性封装拷贝
        for (ProductInfo productInfo : productInfoList) {
            productVoList.add(generateProductVoFromPo(productInfo));
        }
    }
}

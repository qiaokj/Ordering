package com.example.order.service.product.impl;

import com.example.order.common.util.provider.ProductConstantProvider;
import com.example.order.dao.ProductInfoRepository;
import com.example.order.po.ProductInfo;
import com.example.order.service.category.CategoryService;
import com.example.order.service.product.ProductService;
import com.example.order.service.product.util.ProductUtil;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private CategoryService categoryService;

    /**
     * 根据商品类别组状相应的商品信息
     * @param source
     */
    private void packageProductListByCategory(List<ProductCategoryVo<ProductVo>> source) {

        if (source != null && !source.isEmpty()) {
            for (ProductCategoryVo<ProductVo> productCategoryVo : source) {
                // 查询类别下所有商品
                List<ProductInfo> productInfoList = productInfoRepository.findAllByStatueAndCategory(ProductConstantProvider.ProductStatue.ONSALE.getStatue(), productCategoryVo.getType());
                // 初始化商品VO
                List<ProductVo> productVoList = new ArrayList<>();
                if (productInfoList.size() > 0) {
                    ProductUtil.copyFromPoToProductVo(productInfoList, productVoList);
                }
                productCategoryVo.setProductList(productVoList);
            }
        }
    }

    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findAllProductByPage(Integer pageNo, Integer pageSize) {
        logger.info("pageNo: {}, pageSize: {}", pageNo, pageSize);

        PageVo<ProductCategoryVo<ProductVo>> productCategoryVoPage = categoryService.findAll(pageNo, pageSize);
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);

        // 按照指定的商品类型进行组装商品数据
        packageProductListByCategory(productCategoryVoPage.getContent());

        productCategoryVoPage.setPageNo(pageNo);
        productCategoryVoPage.setTotalPages(1);
        productCategoryVoPage.setPageSize(pageSize);

        return productCategoryVoPage;
    }

    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findAllProduct() {

        PageVo<ProductCategoryVo<ProductVo>> allCategories = categoryService.findAll();

        // 按照指定的商品类型进行组装商品数据
        packageProductListByCategory(allCategories.getContent());

        return allCategories;
    }

    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findProductByCategoryType(List<Integer> types) {

        PageVo<ProductCategoryVo<ProductVo>> resultPageVo = new PageVo<>();

        // 校验商品类别是否可用
        if (types == null || types.isEmpty()) {
            resultPageVo.setTotalElements(0L);
            resultPageVo.setContent(null);
            return resultPageVo;
        }

        // 获取类型相关信息
        resultPageVo = categoryService.findAllByCategoryType(types);

        // 根据类别组装商品信息
        packageProductListByCategory(resultPageVo.getContent());

        return resultPageVo;
    }
}

package com.example.order.service.category.impl;

import com.example.order.dao.ProductCategoryRepository;
import com.example.order.po.ProductCategory;
import com.example.order.service.category.CategoryService;
import com.example.order.service.category.util.CategoryUtil;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private ProductCategoryRepository categoryRepository;

//    private List<ProductCategoryVo<ProductVo>> productCategoryVoList;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }



    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findAll(Integer pageNo, Integer pageSize) {

        if ((pageNo == null || pageNo < 0) || (pageSize == null || pageSize < 0)) {
            logger.error("pageNo 或 pageSize 参数无效");
            throw new NullPointerException("pageNo 或 pageSize 参数无效");
        }
        logger.info("查询商品类型 pageNo{}, pageSize{}", pageNo, pageSize);

        //声明返回数据变量
        PageVo<ProductCategoryVo<ProductVo>> pageVo = new PageVo<>();

        // 分页
        PageRequest pageRequest = new PageRequest(pageNo, pageSize);
        Page<ProductCategory> resultPage = categoryRepository.findAll(pageRequest);

        // 获取查询数据
        List<ProductCategory> content = resultPage.getContent();

        // 如果查找数据结果不存在返回空数据
        if (content.isEmpty()) {
            pageVo.setContent(null);
            pageVo.setTotalElements(0L);
            return pageVo;
        }

        // 初始化返回数据基础信息
        List<ProductCategoryVo<ProductVo>> productCategoryVoList = new ArrayList<>();
        // 将 PO 转为 VO
        CategoryUtil.copyFromPoToProductCategoryVo(content, productCategoryVoList);

        pageVo.setContent(productCategoryVoList);
        pageVo.setTotalPages(resultPage.getTotalPages());
        pageVo.setTotalElements(resultPage.getTotalElements());
        pageVo.setPageSize(pageSize);
        pageVo.setPageNo(pageNo);

        return pageVo;
    }

    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findAll() {

        PageVo<ProductCategoryVo<ProductVo>> pageVo = new PageVo<>();

        List<ProductCategory> productCategories = categoryRepository.findAll();
        if (productCategories.isEmpty()) {
            pageVo.setContent(null);
            pageVo.setTotalPages(0);
            pageVo.setTotalElements(0L);
            return pageVo;
        }
        List<ProductCategoryVo<ProductVo>> productCategoryVoList = new ArrayList<>();
        // 将 PO 类转为 VO 类
        CategoryUtil.copyFromPoToProductCategoryVo(productCategories, productCategoryVoList);

        pageVo.setTotalElements((long) productCategoryVoList.size());
        pageVo.setContent(productCategoryVoList);

        return pageVo;
    }

    @Override
    public PageVo<ProductCategoryVo<ProductVo>> findAllByCategoryType(List<Integer> categpryTypes) {

        if (categpryTypes == null || categpryTypes.isEmpty()) {
            PageVo<ProductCategoryVo<ProductVo>> emptyPageVo = new PageVo<>();
            emptyPageVo.setContent(null);
            emptyPageVo.setTotalElements(0L);
            emptyPageVo.setTotalPages(0);
            return emptyPageVo;
        }
        PageVo<ProductCategoryVo<ProductVo>> resultPageVo = new PageVo<>();

        // 查询数据
        List<ProductCategory> categoryList = categoryRepository.findByTypeIn(categpryTypes);

        // 如果存在数据集
        if (categoryList != null && !categoryList.isEmpty()) {
            // 初始化返回数据基础信息
            List<ProductCategoryVo<ProductVo>> productCategoryVoList = new ArrayList<>();

            // 将 PO 类转为 VO 类
            CategoryUtil.copyFromPoToProductCategoryVo(categoryList, productCategoryVoList);

            resultPageVo.setContent(productCategoryVoList);
            resultPageVo.setTotalElements((long) productCategoryVoList.size());
        }

        return resultPageVo;
    }

    @Override
    public void save(ProductCategory productCategory) {
        categoryRepository.save(productCategory);
    }
}

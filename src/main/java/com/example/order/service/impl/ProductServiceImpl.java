package com.example.order.service.impl;

import com.example.order.common.util.ConstantProvider;
import com.example.order.dao.ProductInfoRepository;
import com.example.order.po.ProductInfo;
import com.example.order.service.ProductService;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductVo;
import com.example.order.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductInfoRepository productRepository;

    @Override
    public PageVo<ProductVo> findAllProductByPage(Integer pageNo, Integer pageSize) {
        logger.info("pageNo: {}, pageSize: {}", pageNo, pageSize);
        PageRequest request = new PageRequest(pageNo, pageSize);
        Page<ProductInfo> productInfos = productRepository.findAll(request);
        logger.info("productInfos: {}", productInfos);
        PageVo<ProductVo> page = new PageVo<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        if (productInfos != null) {
            page.setTotalPages(productInfos.getTotalPages());
            page.setTotalElements(productInfos.getTotalElements());

            List<ProductVo> productVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                ProductVo productVo = new ProductVo();
                BeanUtils.copyProperties(productInfo, productVo);
                productVoList.add(productVo);
            }
            page.setContent(productVoList);
        } else {
            page.setTotalPages(0);
            page.setTotalElements(0L);
            page.setContent(new ArrayList<>());
        }
        return page;
    }
}

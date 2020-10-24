package com.example.order.service;

import com.example.order.vo.PageVo;
import com.example.order.vo.ProductVo;

public interface ProductService {

    PageVo<ProductVo> findAllProductByPage(Integer pageNo, Integer pageSize);
}

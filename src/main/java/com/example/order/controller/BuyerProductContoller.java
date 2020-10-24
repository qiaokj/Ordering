package com.example.order.controller;

import com.example.order.po.ProductInfo;
import com.example.order.service.ProductService;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductVo;
import com.example.order.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 买家, 商品请求
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductContoller {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo productList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {

        PageVo<ProductVo> productPage = productService.findAllProductByPage(pageNo, pageSize);

        return ResultVo.successWithData(productPage);
    }

}

package com.example.order.controller;

import com.example.order.service.product.ProductService;
import com.example.order.vo.PageVo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;
import com.example.order.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 买家, 商品请求
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductContoller {

    @Autowired
    private ProductService productService;

    /**
     * http://localhost:8080/sell/buyer/product/list?pageNo=0&pageSize=4
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo productList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {

        PageVo<ProductCategoryVo<ProductVo>> resultPageVo = productService.findAllProductByPage(pageNo, pageSize);

        return ResultVo.successWithData(resultPageVo);
    }

    /**
     * 查询所有已上架的尚商品
     * @return
     */
    @RequestMapping(value = "/list/all")
    public ResultVo allProductList() {

        PageVo<ProductCategoryVo<ProductVo>> allProduct = productService.findAllProduct();

        return ResultVo.successWithData(allProduct);
    }

    /**
     * 按照类型查询指定的商品
     * @param types
     * @return
     */
    @RequestMapping(value = "/list/type", method = RequestMethod.GET)
    public ResultVo productListInType(@RequestParam("type") List<Integer> types) {

        PageVo<ProductCategoryVo<ProductVo>> productByCategoryType = productService.findProductByCategoryType(types);

        return ResultVo.successWithData(productByCategoryType);
    }

}

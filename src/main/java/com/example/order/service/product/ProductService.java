package com.example.order.service.product;

import com.example.order.vo.PageVo;
import com.example.order.vo.ProductCategoryVo;
import com.example.order.vo.ProductVo;

import java.util.List;


public interface ProductService {

    /**
     * 根据分页参数查询商品列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageVo<ProductCategoryVo<ProductVo>> findAllProductByPage(Integer pageNo, Integer pageSize);

    /**
     * 查询所有的商品列表
     * @return
     */
    PageVo<ProductCategoryVo<ProductVo>> findAllProduct();

    /**
     * 根据指定的商品类别进行查询
     * @param types
     * @return
     */
    PageVo<ProductCategoryVo<ProductVo>> findProductByCategoryType(List<Integer> types);

    /**
     * 扣减库存
     * @param productId
     * @param stock
     */
    public void reduceProductStock(String productId, Integer stock);

    /**
     * 增加库存
     * @param productId
     * @param stock
     */
    public void increaseProductStock(String productId, Integer stock);
}

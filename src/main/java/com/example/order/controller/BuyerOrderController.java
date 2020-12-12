package com.example.order.controller;

import com.example.order.dto.OrderInfoDto;
import com.example.order.service.order.OrderService;
import com.example.order.vo.BasicQueryVo;
import com.example.order.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * /sell/buyer/order/*
 */
@RestController
@RequestMapping(value = "/buyer/order")
public class BuyerOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyerOrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVo submitCreateOrder(@RequestBody OrderInfoDto orderInfo) {

        LOGGER.info(orderInfo.toString());

        OrderInfoDto order = orderService.createOrder(orderInfo);

        return ResultVo.successWithData(orderInfo);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @param request
     * @return
     */
    @GetMapping(value = "/query")
    public ResultVo getOrderInfoByOrderId(@RequestParam("orderId") String orderId, HttpServletRequest request) {

        LOGGER.info("请求路径: {}", request.getServletPath());

        OrderInfoDto orderInfo = orderService.queryOrder(orderId);

        return ResultVo.successWithData(orderInfo);
    }

    /**
     * 查询订单列表
     *
     * @param query
     * @return
     */
    @PostMapping(value = "/query")
    public ResultVo getOrderListByQueryVo(@RequestBody BasicQueryVo query, HttpServletRequest request) {

        LOGGER.info("请求路径: {}, 请求参数: {}", request.getServletPath(), query.toString());
        List<OrderInfoDto> orderInfoDtos = orderService.queryOrderList(query);

        return ResultVo.successWithData(orderInfoDtos);
    }


}

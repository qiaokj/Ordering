package com.example.order.controller;

import com.example.order.dto.OrderInfoDto;
import com.example.order.service.order.OrderService;
import com.example.order.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVo submitCreateOrder(@RequestBody OrderInfoDto orderInfo) {

        LOGGER.info(orderInfo.toString());

        OrderInfoDto order = orderService.createOrder(orderInfo);

        return ResultVo.successWithData(orderInfo);
    }

}

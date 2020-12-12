package com.example.order.service.order.impl;

import com.example.order.dao.OrderMasterRepository;
import com.example.order.dto.OrderInfoDto;
import com.example.order.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder() {
    }

    @Test
    public void queryOrder() {
    }

    @Test
    public void queryOrderList() {

        List<OrderInfoDto> orderInfoDtos = orderService.queryOrderList("欧亚峰", "C2020N2091");
        System.out.println(orderInfoDtos);

    }

    @Test
    public void testQueryOrderList() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void payOrder() {
    }

    @Test
    public void acceptOrder() {
    }

    @Test
    public void finishOrder() {
    }

    @Test
    public void testQueryOrderList1() {

    }
}
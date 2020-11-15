package com.example.order.dao;

import com.example.order.common.util.OddNumberGenerator;
import com.example.order.po.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setAmount(new BigDecimal("22.99"));
        orderDetail.setId(OddNumberGenerator.generateOrderDetailId());
        orderDetail.setOrderId("O2020N6751");
        orderDetail.setProductId("P2020N3400");
        orderDetail.setProductQuantity(1);
        orderDetail.setProductPrice(new BigDecimal("22.99"));
        orderDetail.setProductName("羊肉汤");

        orderDetailRepository.save(orderDetail);
    }

    @Test
    public void findAllByOrderId() {
        List<OrderDetail> o2020N6751 = orderDetailRepository.findAllByOrderId("O2020N6751");
        System.out.println(o2020N6751);
    }
}
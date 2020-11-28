package com.example.order.dao;

import com.example.order.po.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * 订单详情表
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    public List<OrderDetail> findAllByOrderId(String orderId);

}

package com.example.order.service.order;

import com.example.order.dto.OrderInfoDto;

import java.util.Date;
import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param orderInfo
     * @return
     */
    public OrderInfoDto createOrder(OrderInfoDto orderInfo);

    /**
     * 根据 Order ID 查询订单
     * @param orderId
     * @return
     */
    public OrderInfoDto queryOrder(String orderId);

    /**
     * 根据用户信息查询订单
     * @param userName
     * @param userId
     * @return
     */
    public List<OrderInfoDto> queryOrderList(String userName, String userId);

    /**
     * 根据订单时间
     * @param orderStartDate
     * @param orderEndDate
     * @return
     */
    public List<OrderInfoDto> queryOrderList(Date orderStartDate, Date orderEndDate);

    public OrderInfoDto cancelOrder(OrderInfoDto orderInfo);

    public OrderInfoDto payOrder();

    public OrderInfoDto acceptOrder(String orderId, String businessId);

    public OrderInfoDto finishOrder(String orderId, String businessId);
}

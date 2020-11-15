package com.example.order.dao;

import com.example.order.po.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 订单主表 DAO
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 根据用户 ID 查询, 分页显示
     * @param customerId
     * @param pageAble
     * @return
     */
    Page<OrderMaster> findOrderMasterByCustomerId(String customerId, Pageable pageAble);

    /**
     * 根据用户 ID 和 订单状态查询, 分页显示
     * @param customerId
     * @param status
     * @param pageAble
     * @return
     */
    Page<OrderMaster> findOrderMasterByCustomerIdAndOrderStatus(String customerId, Integer status, Pageable pageAble);
}

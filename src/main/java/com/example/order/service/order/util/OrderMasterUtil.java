package com.example.order.service.order.util;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.provider.ExceptionEnum;
import com.example.order.dto.OrderInfoDto;
import com.example.order.exception.OrderException;
import com.example.order.po.OrderDetail;
import com.example.order.po.OrderMaster;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiao
 */
public class OrderMasterUtil {

    /**
     *
     * @param orderInfoDto
     * @param orderMaster
     */
    public static void copyFromDtoToOrderMaster(OrderInfoDto orderInfoDto, OrderMaster orderMaster) {

        if (orderMaster == null) {
            orderMaster = new OrderMaster();
        }

        if (orderInfoDto == null) {
            throw new OrderException(ExceptionEnum.ORDER_PROCESS_ERROR);
        }

        orderMaster.setValid(ConstantProvider.VALID);
        orderMaster.setOrderTime(orderInfoDto.getOrderTime());
        orderMaster.setCustomerId(orderInfoDto.getCustomerId());
        orderMaster.setCustomerName(orderInfoDto.getCustomerName());
        orderMaster.setBusinessId(orderInfoDto.getBusinessId());
        orderMaster.setBusinessName(orderInfoDto.getBusinessName());
        orderMaster.setAmount(orderInfoDto.getAmount());
    }

}

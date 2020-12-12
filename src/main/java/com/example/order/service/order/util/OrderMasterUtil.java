package com.example.order.service.order.util;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.provider.ExceptionEnum;
import com.example.order.common.util.provider.OrderConstantProvider;
import com.example.order.dto.OrderInfoDto;
import com.example.order.exception.OrderException;
import com.example.order.po.OrderMaster;

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
        orderMaster.setPayType(OrderConstantProvider.PayStyle.ONLINE_PAY.getCode());
        orderMaster.setAmount(orderInfoDto.getAmount());
    }

    /**
     * 将 PO 类的数据拷贝到 DTO
     * @param orderInfoDto
     * @param orderMaster
     */
    public static void copyFromOrderMasterToDto(OrderInfoDto orderInfoDto, OrderMaster orderMaster) {

        if (orderInfoDto == null) {
            orderInfoDto = new OrderInfoDto();
        }

        if (orderMaster == null) {
            throw new OrderException(ExceptionEnum.ORDER_NOT_EXIST_ERROR);
        }

        orderInfoDto.setId(orderMaster.getId());
        orderInfoDto.setOrderTime(orderMaster.getOrderTime());
        orderInfoDto.setPayType(orderMaster.getPayType());
        orderInfoDto.setCustomerId(orderMaster.getCustomerId());
        orderInfoDto.setCustomerName(orderMaster.getCustomerName());
        orderInfoDto.setBusinessId(orderMaster.getBusinessId());
        orderInfoDto.setBusinessName(orderMaster.getBusinessName());
        orderInfoDto.setAmount(orderMaster.getAmount());
        orderInfoDto.setValid(orderMaster.getValid());
    }

}

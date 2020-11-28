package com.example.order.service.order.impl;

import com.example.order.common.util.CommonUtil;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.common.util.provider.ExceptionEnum;
import com.example.order.dao.*;
import com.example.order.dto.CartDto;
import com.example.order.dto.OrderInfoDto;
import com.example.order.exception.OrderException;
import com.example.order.po.*;
import com.example.order.service.order.OrderService;
import com.example.order.service.order.util.OrderMasterUtil;
import com.example.order.service.product.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Qiao
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderInfoDto createOrder(OrderInfoDto orderInfo) {

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + " 开始创建订单");

        // 订单数据校验与初始化
        orderInfoValidCheckAndInit(orderInfo);
        // 订单金额计算
        List<CartDto> cartList = calculateProductListBill(orderInfo);


        // 订单数据入库
        // 生成订单号与订单状态
        OrderMaster orderMaster = new OrderMaster();
        OrderMasterUtil.copyFromDtoToOrderMaster(orderInfo, orderMaster);
        orderMaster.setId(OddNumberGenerator.generateOrderId());
        OrderMaster master = orderMasterRepository.save(orderMaster);

        // 订单详情数据保存
        for (OrderDetail orderDetail : orderInfo.getOrderDetailList()) {
            orderDetail.setId(OddNumberGenerator.generateOrderDetailId());
            orderDetail.setOrderId(orderMaster.getId());
            OrderDetail detail = orderDetailRepository.save(orderDetail);
        }

        // 扣减库存
        for (CartDto cartDto : cartList) {
            productService.reduceProductStock(cartDto.getProductId(), cartDto.getQuantity());
        }

        // 组装返回数据
        orderInfo.setId(orderMaster.getId());
        orderInfo.setPayStatus(orderMaster.getPayStatus());
        orderInfo.setOrderStatus(orderMaster.getOrderStatus());
        orderInfo.setPayType(orderMaster.getPayType());

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + " 完成创建订单");

        return orderInfo;
    }

    /**
     * 订单总价计算, 并生成库存扣减数据
     * @param orderInfo
     */
    private List<CartDto> calculateProductListBill(OrderInfoDto orderInfo) {

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<CartDto> cartList = new ArrayList<>();

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + ", 在 " + CommonUtil.parseDateToFormatStr(new Date(), null) +" 开始计算订单总金额");

        for (OrderDetail orderDetail : orderInfo.getOrderDetailList()) {

            // 计算商品明细金额
            BigDecimal quantity = new BigDecimal(orderDetail.getProductQuantity().toString());
            BigDecimal result = orderDetail.getProductPrice().multiply(quantity);
            orderDetail.setAmount(result);

            // 计算订单金额
            totalAmount = totalAmount.add(result);

            // 生成库存扣减数据
            CartDto cartDto = new CartDto();
            cartDto.setProductId(orderDetail.getProductId());
            cartDto.setQuantity(orderDetail.getProductQuantity());
            cartList.add(cartDto);

            LOGGER.info("线程 ID: " + Thread.currentThread().getId() + "    {}({}) 数量 {} 计算金额为: {}", orderDetail.getProductName(), orderDetail.getProductId(), quantity, result.toString());
        }
        // 设置订单金额
        orderInfo.setAmount(totalAmount);

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + ", 在 " + CommonUtil.parseDateToFormatStr(new Date(), null) +" 完成计算订单总金额, 订单总金额 {}", totalAmount);


        // todo 优惠金额计算

        return cartList;
    }

    /**
     * 订单数据有效性校验
     * @param orderInfo
     */
    private void orderInfoValidCheckAndInit(OrderInfoDto orderInfo) {

        // 订单数据是否存在
        if (orderInfo == null) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID);
        }

        // 订单时间校验
        Date orderTime = orderInfo.getOrderTime();
        if (orderTime == null || CommonUtil.checkDateAfterTarget(orderTime, new Date())) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "订单时间异常");
        }

        // 商品信息校验
        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        if (orderDetailList == null || orderDetailList.isEmpty()) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "订单商品数据异常");
        }
        for (OrderDetail orderDetail : orderDetailList) {
            if (orderDetail != null) {

                String productId = orderDetail.getProductId();
                String productName = orderDetail.getProductName();
                Integer productQuantity = orderDetail.getProductQuantity();

                ProductInfo productInfo = productInfoRepository.findOneByIdAndName(productId, productName);
                if (productInfo == null) {
                    throw new OrderException(ExceptionEnum.PRODUCT_INFO_ERROR);
                }

                if (productQuantity == null || productQuantity <= 0) {
                    throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "订单商品数量信息异常");
                }

                orderDetail.setProductPrice(productInfo.getPrice());

            } else {
                throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "订单商品数据异常");
            }
        }

        // 顾客信息校验
        String customerId = orderInfo.getCustomerId();
        String customerName = orderInfo.getCustomerName();
        if (StringUtils.isBlank(customerId) || StringUtils.isBlank(customerName)) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "用户数据异常");
        }
        CustomerInfo customerInfo = customerInfoRepository.findOneByIdAndName(customerId, customerName);
        if (customerInfo == null) {
            throw new OrderException(ExceptionEnum.USER_INFO_ERROR);
        }

        // 商家信息校验
        String businessId = orderInfo.getBusinessId();
        String businessName = orderInfo.getBusinessName();
        if (StringUtils.isBlank(businessId) || StringUtils.isBlank(businessName)) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID.getCode(), ExceptionEnum.ORDER_PARAM_INVALID.getMsg(), "商户数据异常");
        }
        BusinessInfo businessInfo = businessInfoRepository.findOneByIdAndName(businessId, businessName);
        if (businessInfo == null) {
            throw new OrderException(ExceptionEnum.BUSINESS_INFO_ERROR);
        }

    }

    @Override
    public OrderInfoDto queryOrder(String orderId) {
        return null;
    }

    @Override
    public List<OrderInfoDto> queryOrderList(String userName, String userId) {
        return null;
    }

    @Override
    public List<OrderInfoDto> queryOrderList(Date orderStartDate, Date orderEndDate) {
        return null;
    }

    @Override
    public OrderInfoDto cancelOrder(OrderInfoDto orderInfo) {
        return null;
    }

    @Override
    public OrderInfoDto payOrder() {
        return null;
    }

    @Override
    public OrderInfoDto acceptOrder(String orderId, String businessId) {
        return null;
    }

    @Override
    public OrderInfoDto finishOrder(String orderId, String businessId) {
        return null;
    }
}

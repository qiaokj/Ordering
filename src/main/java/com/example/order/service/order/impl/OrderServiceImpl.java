package com.example.order.service.order.impl;

import com.example.order.common.util.CommonUtil;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.common.util.provider.ExceptionEnum;
import com.example.order.common.util.provider.OrderConstantProvider;
import com.example.order.dao.*;
import com.example.order.dto.CartDto;
import com.example.order.dto.OrderInfoDto;
import com.example.order.exception.CustomerException;
import com.example.order.exception.OrderException;
import com.example.order.exception.SystemClientException;
import com.example.order.po.*;
import com.example.order.service.order.OrderService;
import com.example.order.service.order.util.OrderMasterUtil;
import com.example.order.service.product.ProductService;
import com.example.order.vo.BasicQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
     *
     * @param orderInfo
     */
    private List<CartDto> calculateProductListBill(OrderInfoDto orderInfo) {

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<CartDto> cartList = new ArrayList<>();

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + ", 在 " + CommonUtil.parseDateToFormatStr(new Date(), null) + " 开始计算订单总金额");

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

        LOGGER.info("线程 ID: " + Thread.currentThread().getId() + ", 在 " + CommonUtil.parseDateToFormatStr(new Date(), null) + " 完成计算订单总金额, 订单总金额 {}", totalAmount);


        // todo 优惠金额计算

        return cartList;
    }

    /**
     * 订单数据有效性校验
     *
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

        if (StringUtils.isBlank(orderId)) {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID);
        }

        // 查询订单信息
        OrderMaster master = orderMasterRepository.findOne(orderId);
        if (master == null) {
            throw new OrderException(ExceptionEnum.ORDER_NOT_EXIST_ERROR);
        }

        // 查询订单明细信息
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrderId(master.getId());
        if (orderDetailList == null || orderDetailList.isEmpty()) {
            throw new OrderException(ExceptionEnum.ORDERDETAIL_NOT_EXIST_ERROR);
        }

        OrderInfoDto orderInfoDto = new OrderInfoDto();
        OrderMasterUtil.copyFromOrderMasterToDto(orderInfoDto, master);

        orderInfoDto.setOrderDetailList(orderDetailList);

        return orderInfoDto;
    }

    @Override
    public List<OrderInfoDto> queryOrderList(String userName, String userId) {

        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userId)) {
            throw new CustomerException(ExceptionEnum.USER_INFO_NOT_EXIST_ERROR);
        }

        List<OrderInfoDto> orderMasetList = new ArrayList<>();

        // 默认取出所有的数据
        PageRequest pageRequest = new PageRequest(0, 1000);

        Page<OrderMaster> orderMasetPage = orderMasterRepository.findOneByCustomerNameLikeAndCustomerIdOrderByOrderTime(userName, userId, pageRequest);
        if (orderMasetPage.getTotalElements() == 0) {
            return orderMasetList;
        }

        for (OrderMaster orderMaster : orderMasetPage.getContent()) {
            OrderInfoDto orderInfo = new OrderInfoDto();
            OrderMasterUtil.copyFromOrderMasterToDto(orderInfo, orderMaster);
            orderMasetList.add(orderInfo);
        }

        return orderMasetList;
    }

    @Override
    public List<OrderInfoDto> queryOrderList(Date orderStartDate, Date orderEndDate) {
        return null;
    }

    @Override
    public List<String> queryWaitPayOrderList() {

        // 计算订单超时基准时间
        Date date = CommonUtil.subTime(new Date(), OrderConstantProvider.DEFAULT_PAY_TIMEOUT);

        // 每次从数据库查询10条数据
        PageRequest pageRequest = new PageRequest(0, 10);

        // 查询已经超时的未支付订单
        Page<OrderMaster> waitPayOrderPage = orderMasterRepository.
                findOrderMasterByOrderTimeBeforeAndPayStatusAndValid(date, OrderConstantProvider.PayStatus.WAIT_PAYMENT.getCode(),OrderConstantProvider.ORDER_VALID, pageRequest);

        List<String> orderIdList = new ArrayList<>();

        List<OrderMaster> waitPayOrderList = waitPayOrderPage.getContent();
        if (waitPayOrderList.size() > 0) {
            for (OrderMaster orderMaster : waitPayOrderList) {
                orderIdList.add(orderMaster.getId());
            }
        }

        LOGGER.info("{} 查询未支付的订单总数量为 {}", CommonUtil.parseDateToFormatStr(date, "yyyy-MM-dd HH:mm:ss"), waitPayOrderPage.getTotalElements());

        return orderIdList;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderInfoDto cancelOrder(String orderId) {

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderId(orderId);

        for (OrderDetail orderDetail : orderDetails) {

            // 对订单中的库存等数据进行复原, 如果商品已经不存（过期或删除）在则不进行任何操作
            String productId = orderDetail.getProductId();
            productService.increaseProductStock(productId, orderDetail.getProductQuantity());
        }

        this.updateOrderStatus(orderId, OrderConstantProvider.OrderStatus.ORDER_CANCEL.getStatus(), OrderConstantProvider.ORDER_INVALID);

        return this.queryOrder(orderId);
    }

    /**
     * 将订单设置为取消的状态
     * @param orderId
     * @param status
     * @param isValid
     */
    private void updateOrderStatus(String orderId, int status, Integer isValid) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new OrderException(ExceptionEnum.ORDER_NOT_EXIST_ERROR);
        }

        // 校验需要设置的订单状态
        if (status > 0 && (status % 2 == 0)) {

        } else {
            throw new OrderException(ExceptionEnum.ORDER_PARAM_INVALID);
        }

        orderMaster.setOrderStatus(status);
        orderMaster.setValid(isValid);

        orderMasterRepository.save(orderMaster);
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

    @Override
    public List<OrderInfoDto> queryOrderList(BasicQueryVo queryVo) {

        if (queryVo == null) {
            throw new SystemClientException(ExceptionEnum.SYSTEM_CLIENT_EXCEPTION);
        }

        String customerName = queryVo.getCustomerName();
        String customerId = queryVo.getCustomerId();

        return this.queryOrderList(customerName, customerId);
    }
}

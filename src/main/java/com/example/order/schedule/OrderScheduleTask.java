package com.example.order.schedule;

import com.example.order.common.util.CommonUtil;
import com.example.order.service.order.OrderService;
import com.example.order.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 订单定时任务
 */
@Component
public class OrderScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(OrderScheduleTask.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    /**
     * 将超时的未支付订单进行关闭
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void closeOrderByOverTime() {

        long startProcessTime = System.currentTimeMillis();
        log.info("{} 订单支付超时处理于 {} 开始执行", Thread.currentThread().getId(), CommonUtil.parseDateToFormatStr(new Date(startProcessTime), null));

        List<String> waitPayOrderIdList = orderService.queryWaitPayOrderList();

        for (String orderId : waitPayOrderIdList) {
            orderService.cancelOrder(orderId);
            log.info("订单 {} 已经执行支付超时注销操作", orderId);
        }

        log.info("{} 订单支付超时处理于 {} 执行完毕, 总耗时 {} 毫秒", Thread.currentThread().getId(), CommonUtil.parseDateToFormatStr(new Date(), null), System.currentTimeMillis() - startProcessTime);
    }

}

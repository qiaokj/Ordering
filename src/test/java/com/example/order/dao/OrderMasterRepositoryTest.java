package com.example.order.dao;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.common.util.provider.OrderConstantProvider;
import com.example.order.po.BusinessInfo;
import com.example.order.po.CustomerInfo;
import com.example.order.po.OrderMaster;
import com.example.order.po.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private BusinessInfoRepository businessInfoRepository;

    @Test
    public void saveTest() {

        ProductInfo p2020N3400 = productInfoRepository.findOne("P2020N3400");

        BusinessInfo businessInfo = businessInfoRepository.findOne(p2020N3400.getBusinessId());

        CustomerInfo c2020N2091 = customerInfoRepository.findOne("C2020N2091");

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setId(OddNumberGenerator.generateOrderId());
        orderMaster.setBusinessId(p2020N3400.getBusinessId());
        orderMaster.setBusinessName(businessInfo.getName());
        orderMaster.setCustomerId(c2020N2091.getId());
        orderMaster.setCustomerName(c2020N2091.getName());
        orderMaster.setAmount(p2020N3400.getPrice());
        orderMaster.setPayType(OrderConstantProvider.PayStyle.ONLINE_PAY.getCode());
        orderMaster.setOrderTime(new Date());

        orderMasterRepository.save(orderMaster);
    }

    @Test
    public void findOrderMasterByCustomerId() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> c2020N2091 = orderMasterRepository.findOrderMasterByCustomerId("C2020N2091", pageRequest);
        System.out.println(c2020N2091.getContent());
    }

    @Test
    public void findOrderMasterByCustomerIdAndOrderStatus() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMaster> c2020N2091 = orderMasterRepository.
                findOrderMasterByCustomerIdAndOrderStatus("C2020N2091",2, pageRequest);
        System.out.println(c2020N2091.getContent());
    }


}
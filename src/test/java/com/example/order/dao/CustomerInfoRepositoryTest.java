package com.example.order.dao;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.po.CustomerInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerInfoRepositoryTest {

    @Autowired
    private CustomerInfoRepository repository;

    @Test
    public void saveTest() {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(OddNumberGenerator.generateCustomerId());
        customerInfo.setIdentifyNo(OddNumberGenerator.generateIdentifyNumber());
        customerInfo.setName("欧亚峰");
        customerInfo.setGender(ConstantProvider.CustomerGender.MALE.getCode());
        String openId = UUID.randomUUID().toString()+ UUID.randomUUID().toString();
        customerInfo.setOpenId(openId.substring(0, 64));
        customerInfo.setPhone(OddNumberGenerator.generatePhoneNumber());
        customerInfo.setPoints(0);
        customerInfo.setRecord(0);
        customerInfo.setValid(ConstantProvider.VALID);

        repository.save(customerInfo);
    }
}
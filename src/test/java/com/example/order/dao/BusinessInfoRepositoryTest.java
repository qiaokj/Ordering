package com.example.order.dao;

import com.example.order.common.util.ConstantProvider;
import com.example.order.common.util.OddNumberGenerator;
import com.example.order.po.BusinessInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BusinessInfoRepositoryTest {

    @Autowired
    private BusinessInfoRepository repository;

    @Test
    public void save() {

        BusinessInfo businessInfo = new BusinessInfo();
        businessInfo.setId(OddNumberGenerator.generateBusinessId());
        businessInfo.setName("清味斋");
        businessInfo.setSales(185);
        businessInfo.setScore(2.7);
        businessInfo.setAddress("蒲黄榆二里5号楼");
        businessInfo.setPhone(OddNumberGenerator.generatePhoneNumber());
        businessInfo.setLinkerName("清味斋");
        businessInfo.setLinkerIdentify(OddNumberGenerator.generateIdentifyNumber());
        businessInfo.setScope("羊汤/汤包/包子");
        businessInfo.setIndustryId(OddNumberGenerator.generateIndustryId());

        repository.save(businessInfo);
    }

    @Test
    public void testFind() {
        BusinessInfo b2020N5475 = repository.findOne("B2020N5475");
        System.out.println(b2020N5475);
    }
}
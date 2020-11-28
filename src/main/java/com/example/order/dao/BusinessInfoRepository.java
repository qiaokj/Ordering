package com.example.order.dao;

import com.example.order.po.BusinessInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Qiao
 */
public interface BusinessInfoRepository extends JpaRepository<BusinessInfo, String> {

    /**
     * 根据商户 ID 和商户名称查询
     * @param businessId
     * @param businessName
     * @return
     */
    BusinessInfo findOneByIdAndName(String businessId, String businessName);
}

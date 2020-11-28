package com.example.order.dao;

import com.example.order.po.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Qiao
 */
@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, String> {

    /**
     * 根据用户 ID 和姓名查询数据库
     * @param id
     * @param username
     * @return
     */
    CustomerInfo findOneByIdAndName(String id, String username);
}

package com.example.order.dao;

import com.example.order.po.WalletInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletInfoRepository extends JpaRepository<WalletInfo, String> {
}

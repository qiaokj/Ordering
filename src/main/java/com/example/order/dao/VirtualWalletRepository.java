package com.example.order.dao;

import com.example.order.po.VirtualWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualWalletRepository extends JpaRepository<VirtualWallet, String> {
}

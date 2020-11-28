package com.example.order.dao;

import com.example.order.common.util.OddNumberGenerator;
import com.example.order.common.util.provider.OrderConstantProvider;
import com.example.order.po.VirtualWallet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VirtualWalletRepositoryTest {

    @Autowired
    private VirtualWalletRepository  virtualWalletRepository;

    @Test
    public void testSave() {

        VirtualWallet virtualWallet = new VirtualWallet();
        virtualWallet.setTradeNo(OddNumberGenerator.generateVirtualWalletTradeNo());
        virtualWallet.setTradeTime(new Date());
        virtualWallet.setTradeAmount(BigDecimal.TEN);
        virtualWallet.setTradeType(OrderConstantProvider.WalletTradeType.VIRTUAL_TRADE_RECHARGE.getCode());
        virtualWallet.setUserId("C2020N2091");
        virtualWallet.setVirtualWalletId(OddNumberGenerator.generateVirtualWalletId("C2020N2091"));
        virtualWallet.setWalletTradeNo(OddNumberGenerator.generateWalletTradeNo());

        virtualWalletRepository.save(virtualWallet);

    }

}
package com.example.order;

import com.example.order.common.util.ConstantProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderingApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(ConstantProvider.class);

    @Test
    public void contextLoads() {
        logger.info("info...");
        logger.debug("debug...");
        logger.error("error..");
    }

    @Test
    public void testLog() {
        String name = "Ming";
        String password = "password";

        logger.info("name: {}, password: {}", name, password);
        logger.info("name: {}, password: {}", name, password);
        logger.info("name: {}, password: {}", name, password);

        logger.debug("debug ....");

        logger.error("testLog() error");
    }

}

package com.dhb;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootElkApplicationTests {

    @Test
    void contextLoads() {
    }

    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void test()throws Exception{
        for(int i = 0;i < 100;i++){
            logger.info("输出info");
            logger.debug("输出debug+skkkw嗡嗡嗡kw");
            logger.error("输出error 嗡嗡嗡我");
        }
    }
}

package com.lw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyanqiang
 * @title: LwApplication
 * @date 2020/4/3015:15
 */
@SpringBootApplication
public class LwApplication {

   private static final Logger logger = LoggerFactory.getLogger(LwApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(LwApplication.class,args);
        logger.info("******************************* system start successfully *******************************");
    }
}

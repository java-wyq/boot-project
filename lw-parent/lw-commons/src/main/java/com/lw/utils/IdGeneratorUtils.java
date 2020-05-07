package com.lw.utils;

import com.lw.config.snowflake.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangyanqiang
 * @title: IdGenerator
 * @date 2020/5/710:05
 */
@Component
public class IdGeneratorUtils {
    private static volatile SnowflakeIdWorker instance;
    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorUtils.class);
    private static Long workerId;
    private static Long dataCenterId;
    @Value("${snowflake.workerId}")
    public void setwId(Long workerId) {
        IdGeneratorUtils.workerId = workerId;
    }
    @Value("${snowflake.dataCenterId}")
    public void setdId(Long dataCenterId) {
        IdGeneratorUtils.dataCenterId = dataCenterId;
    }

    public static SnowflakeIdWorker getInstance() {
        if (instance == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (instance == null) {
                    logger.info("");
                    System.out.println(String.format("snowflake id generator initialize : workId = %d, datacenterId = %d", workerId, dataCenterId));
                    instance = new SnowflakeIdWorker(workerId, dataCenterId);
                }
            }
        }
        return instance;
    }

    public static Long getDataCenterId() {
        return dataCenterId;
    }
}

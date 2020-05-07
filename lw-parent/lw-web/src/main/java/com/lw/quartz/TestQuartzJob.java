package com.lw.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author wangyanqiang
 * @title: TestQuartzJob
 * @date 2020/5/611:47
 */
public class TestQuartzJob extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(TestQuartzJob.class);
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("test quartz every one minite");
    }
}

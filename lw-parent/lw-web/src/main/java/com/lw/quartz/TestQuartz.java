package com.lw.quartz;

import com.lw.utils.QuartzJobUtils;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangyanqiang
 * @title: TestQuartz
 * @date 2020/5/611:44
 */
@Component
public class TestQuartz implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(TestQuartz.class);
    @Autowired
    Scheduler scheduler;
    @Value("${lw.test_cron_exp}")
    private String CRON_EXP;

    @Override
    public void run(String... args) throws Exception {
        logger.info("test start quartz ....");
        JobKey jobKey = new JobKey("test quartz","test");
        JobDetail testQuartz = scheduler.getJobDetail(jobKey);
        if(testQuartz !=null){
            logger.info("quartz job ："+testQuartz.getKey() +" already exist !");
            logger.info("pause and delete quartz job ："+testQuartz.getKey() + " ...");
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        }
        QuartzJobUtils.createJobByCron(scheduler,"test quartz","test",CRON_EXP,TestQuartzJob.class);
    }

}

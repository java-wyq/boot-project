package com.lw.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.quartz.*;
import java.util.Date;

/**
 * @author wangyanqiang
 * @title: JobUtils
 * @date 2020/5/6 10:01
 */
public class QuartzJobUtils {

    /**
     *
     * @param scheduler quartz调度器
     * @param startAtTime 任务执行时刻
     * @param name 任务名称
     * @param group 任务组名称
     * @param jobBean 具体任务
     */
    public static void createJobByStartAtTime(Scheduler scheduler, long startAtTime, String name, String group, Class jobBean) throws SchedulerException {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).startAt(new Date(startAtTime)).build();
        createJob(scheduler, name, group, trigger,jobBean);

    }
    /**
     *
     * @param scheduler quartz调度器
     * @param name 任务名称
     * @param group 任务组名称
     * @param cron cron表达式
     * @param jobBean 具体任务
     */
    public static void createJobByCron(Scheduler scheduler, String name, String group,String cron,Class jobBean) throws SchedulerException {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        createJob(scheduler,name,group,trigger,jobBean);
    }

    private static void createJob(Scheduler scheduler, String name, String group, Trigger trigger,Class jobBean) throws SchedulerException {
        JobKey jobKey = new JobKey(name,group);
        JobDetail job = scheduler.getJobDetail(jobKey);
        if(!ObjectUtils.isNotEmpty(job)){
            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(jobBean).withIdentity(name,group).build();
            try {
                //将任务与触发器绑定到调度器内
                scheduler.scheduleJob(jobDetail, trigger);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }else {
            throw new SchedulerException("quartz job ："+ group +"." + name  + " already exist !");
        }

    }
}

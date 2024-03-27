package com.cloud.timer.common;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2024/2/29 10:08
 * @mark SchedulerTs
 */
public class SchedulerTs implements Job {

    public static void main(String[] args) throws SchedulerException {

        JobDetail job = JobBuilder.newJob(SchedulerTs.class)
                .withIdentity("job1", "group1")
                .usingJobData("name", "zs")
                .build();
        Date endDate = DateUtils.addMinutes(new Date(), 2);
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger", "triggerGroup")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        // 任务执行间隔
                        .withIntervalInSeconds(10)
                        // 重复执行次数
                        .withRepeatCount(10))
                .forJob(job)
                .endAt(endDate)
                .startNow()
                .build();


        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        // 配置任务
        scheduler.scheduleJob(job, trigger);
        scheduler.start();



    }


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object o = context.get("name");
        System.out.println("定时任务执行" + o);
    }
}

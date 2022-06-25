package com.cloud.job.ts;

import com.cloud.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author haizhuangbu
 * @date 2022/6/25 14:40
 * @mark Main 任务运行
 */
public class Main {


    public static void main(String[] args) throws SchedulerException {

        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "job1")
                .build();


        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30).repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);

        System.out.println(System.currentTimeMillis());

        scheduler.start();


        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scheduler.shutdown();

    }


}

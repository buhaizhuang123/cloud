package com.cloud.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author haizhuangbu
 * @date 2022/6/25 14:40
 * @mark MyJob
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("自定义任务 ------ 任务被执行");
    }
}

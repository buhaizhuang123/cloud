package com.bu.common.task;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author haizhuangbu
 * @date 2023/6/15 16:53
 * @mark PrintServiceTask
 */
public class PrintServiceTask implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        if (delegateExecution.isActive()) {
            System.out.println("执行输出 {被激活}");
        }
    }
}

package com.cloud.acti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author haizhuangbu
 * @date 2023/6/13 16:11
 * @mark ActivitiSuccCall
 */
public class ActivitiSuccCall implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("审批通过了");
    }
}

package com.ts.impl;

import com.ts.LcApplyLimitChain;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:58
 * @mark SendMsgChain
 */
public class SendMsgChain extends LcApplyLimitChain<List<String>, String> {


    @Override
    protected String run(List<String> data) {
        System.out.println(" 传参: " + data);
        System.out.println(" ========= 短信发送节点完成 ============");
        return "任务链执行完成";
    }
}

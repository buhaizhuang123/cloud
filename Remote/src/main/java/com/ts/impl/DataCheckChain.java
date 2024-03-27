package com.ts.impl;

import com.ts.LcApplyLimitChain;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:57
 * @mark DataCheckChain
 */
public class DataCheckChain extends LcApplyLimitChain<String, String> {

    @Override
    protected String run(String data) {
        System.out.println(" 传参: " + data);
        System.out.println(" ========= 数据校验节点执行完成 ============");
        return "name is zs";
    }
}

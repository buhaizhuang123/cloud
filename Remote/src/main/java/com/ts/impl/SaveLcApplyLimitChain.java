package com.ts.impl;

import com.ts.LcApplyLimitChain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:59
 * @mark SaveLcApplyLimitChain
 */
public class SaveLcApplyLimitChain extends LcApplyLimitChain<String, Map> {

    @Override
    protected Map run(String data) {
        System.out.println(" 传参: " + data);
        System.out.println(" ========= 保存贷款申请信息节点完成 ============");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("name", "zs");
        return stringStringHashMap;
    }
}

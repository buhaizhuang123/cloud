package com.ts.impl;

import com.ts.LcApplyLimitChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:56
 * @mark CreditLimitChain
 */
public class CreditLimitChain extends LcApplyLimitChain<Map<String,String>, List<String>> {

    @Override
    protected List run(Map<String, String> data) {
        System.out.println(" 传参: " + data);
        System.out.println(" ========= 授信节点执行 =========");
        List<String> collect = data.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        return collect;
    }
}

package com.ts;

import com.ts.impl.CreditLimitChain;
import com.ts.impl.DataCheckChain;
import com.ts.impl.SaveLcApplyLimitChain;
import com.ts.impl.SendMsgChain;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:59
 * @mark LcApplyLimitChainTs
 */
public class LcApplyLimitChainTs {


    public static void main(String[] args) {

        LcApplyLimitChain chain = new LcApplyLimitChain.Builder()
                .next(new DataCheckChain())
                .next(new SaveLcApplyLimitChain())
                .next(new CreditLimitChain())
                .next(new SendMsgChain()).build();

        chain.doRun("开始节点传参");
    }

}

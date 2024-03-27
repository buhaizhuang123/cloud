package com.ts;

import org.junit.platform.commons.logging.LoggerFactory;

import java.util.logging.Logger;

/**
 * @author haizhuangbu
 * @date 2024/1/20 13:48
 * @mark LcApplyLimitChain<T, R>
 */
public abstract class LcApplyLimitChain<T, R> {


    private LcApplyLimitChain chain;

    // 记录上一节点参数,传递给下一节点
    private R returnVal;


    /**
     * @param chain 下一节点
     */
    public void nextChain(LcApplyLimitChain chain) {
        this.chain = chain;
    }

    protected abstract R run(T data);

    public void doRun(Object data) {
        T param;
        try {
            param = (T) data;
//            . 防止类型转换异常
        } catch (ClassCastException e) {
            // 类型转换异常,任务链组装失败
            return;
        }
        this.returnVal = this.run(param);
        if (chain != null) {
            chain.doRun(this.returnVal);
        }
    }


    public static class Builder {


        // 头节点
        private LcApplyLimitChain headChain;

        // 下一节点
        private LcApplyLimitChain tailChain;


        public Builder next(LcApplyLimitChain chain) {
            // 第一个环节,头尾只有一个
            if (headChain == null && tailChain == null) {
                this.headChain = tailChain = chain;
                return this;
            }
            // 下一环节,节点
            this.tailChain.nextChain(chain);
            this.tailChain = chain;
            return this;
        }

        public LcApplyLimitChain build() {
            return headChain;
        }


    }


}

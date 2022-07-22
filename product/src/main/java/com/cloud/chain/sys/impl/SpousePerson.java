package com.cloud.chain.sys.impl;

import com.cloud.chain.sys.PersonChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author haizhuangbu
 * @date 2022/6/20 15:46
 * @mark SpousePerson
 */
public class SpousePerson extends PersonChain {


    private Logger logger = LoggerFactory.getLogger(SpousePerson.class);


    @Override
    public void doExecute() {
        logger.info("配偶校验");
        if (this.personChain != null) {
            this.personChain.doExecute();
        }
    }
}

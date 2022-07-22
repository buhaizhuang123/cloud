package com.cloud.chain.sys.impl;

import com.cloud.chain.sys.PersonChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * @author haizhuangbu
 * @date 2022/6/20 15:44
 * @mark MainPerson
 */
public class MainPerson extends PersonChain {



    private Logger logger = LoggerFactory.getLogger(MainPerson.class);

    @Override
    public void doExecute() {
        logger.info("主申人校验");
        if (super.personChain != null) {
            super.personChain.doExecute();
        }
    }
}

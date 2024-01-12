package com.cloud.batch.processor;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author haizhuangbu
 * @date 2022/10/7 09:35
 * @mark MyitemProcessor
 */
public class MyitemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String s) throws Exception {

        return s + "processor";
    }
}

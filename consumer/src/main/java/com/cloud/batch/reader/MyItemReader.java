package com.cloud.batch.reader;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author haizhuangbu
 * @date 2022/10/7 09:31
 * @mark MyItemReader
 */
public class MyItemReader implements ItemReader<String> {
    @Override
    public String read()  {
        return RandomStringUtils.randomAlphabetic(10);
    }
}

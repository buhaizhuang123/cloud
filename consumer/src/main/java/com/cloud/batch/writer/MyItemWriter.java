package com.cloud.batch.writer;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/7 09:37
 * @mark MyItemWriter
 */
public class MyItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String data : list) {
            System.out.println("data = " + data);
        }


    }
}

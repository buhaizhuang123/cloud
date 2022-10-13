package com.cloud.batch.processor;

import com.cloud.batch.dto.BusInfo;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author haizhuangbu
 * @date 2022/10/13 11:54
 * @mark BusItemWriter
 */
public class BusItemProcessor implements ItemProcessor<BusInfo, BusInfo> {


    @Override
    public BusInfo process(BusInfo busInfo) throws Exception {
        System.out.println("busInfo = " + busInfo);
        return busInfo;
    }
}

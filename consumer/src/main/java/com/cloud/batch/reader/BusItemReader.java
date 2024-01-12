package com.cloud.batch.reader;

import com.cloud.batch.common.BusSts;
import com.cloud.batch.dao.BusInfoMapper;
import com.cloud.batch.dto.BusInfo;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/12 09:12
 * @mark BusItemReader
 */
// @Component
public class BusItemReader implements ItemReader<BusInfo> {
    @Autowired
    private BusInfoMapper busInfoMapper;

    @Override
    public BusInfo read() {
        BusInfo info = busInfoMapper.findOne(BusSts.OK.getCode());
        return info;
    }
}

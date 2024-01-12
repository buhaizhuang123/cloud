package com.cloud.batch.writer;

import com.cloud.batch.common.BusSts;
import com.cloud.batch.dao.BusInfoMapper;
import com.cloud.batch.dto.BusInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/13 11:53
 * @mark BusItemWriter
 */
@Slf4j
public class BusItemWriter implements ItemWriter<BusInfo> {
    @Autowired
    private BusInfoMapper busInfoMapper;


    @Override
    public void write(List<? extends BusInfo> list) throws Exception {
        log.info("更新状态--------------------");
        list.stream().peek(map -> map.setBusSts(BusSts.UN.getCode())).forEach(busInfoMapper::updateSts);
    }
}

package com.cloud.batch.dao;

import com.cloud.batch.dto.BusInfo;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/12 09:02
 * @mark BusInfoMapper
 */
public interface BusInfoMapper {

    List<BusInfo> findList(BusInfo busInfo);

    BusInfo findOne(String sts);

    Integer insertOne(BusInfo busInfo);

}

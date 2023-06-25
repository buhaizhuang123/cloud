package com.bu.deploy.service;

import com.bu.deploy.dto.BusDeployInfoDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/20 10:46
 * @mark BusDeployInfoService
 */
public interface BusDeployInfoService {

    Integer insert(BusDeployInfoDto busDeployInfoDto);


    List<BusDeployInfoDto> listBusDeployInfo(BusDeployInfoDto busDeployInfoDto, Integer pageNum, Integer pageSize);

}

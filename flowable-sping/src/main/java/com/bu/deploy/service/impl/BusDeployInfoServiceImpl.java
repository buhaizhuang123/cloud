package com.bu.deploy.service.impl;

import com.bu.deploy.common.DeploySts;
import com.bu.deploy.dao.BusDeployInfoMapper;
import com.bu.deploy.dto.BusDeployInfoDto;
import com.bu.deploy.service.BusDeployInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/20 10:47
 * @mark BusDeployInfoServiceImpl
 */
@Service
public class BusDeployInfoServiceImpl implements BusDeployInfoService {

    @Autowired
    private BusDeployInfoMapper busDeployInfoMapper;

    @Override
    public Integer insert(BusDeployInfoDto busDeployInfoDto) {
        busDeployInfoDto.setDeployStatus(DeploySts.INIT.getCode());

        return busDeployInfoMapper.insert(busDeployInfoDto);
    }

    @Override
    public List<BusDeployInfoDto> listBusDeployInfo(BusDeployInfoDto busDeployInfoDto, Integer pageNum, Integer pageSize) {
        return busDeployInfoMapper.listBusDeployInfo(busDeployInfoDto,pageNum,pageSize);
    }
}

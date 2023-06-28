package com.bu.deploy.dao;

import com.bu.deploy.dto.BusDeployInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/20 10:38
 * @mark BusDeployInfoMapper 流程部署信息
 */
public interface BusDeployInfoMapper {

    Integer insert(BusDeployInfoDto busDeployInfoDto);

    List<BusDeployInfoDto> listBusDeployInfo(@Param("deployInfo") BusDeployInfoDto busDeployInfoDto, @Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    BusDeployInfoDto findBusDeployInfoByName(@Param("busName") String busName);
}

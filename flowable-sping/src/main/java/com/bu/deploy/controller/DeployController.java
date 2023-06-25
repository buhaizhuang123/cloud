package com.bu.deploy.controller;

import com.bu.common.po.ResultPo;
import com.bu.deploy.dto.BusDeployInfoDto;
import com.bu.deploy.service.BusDeployInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/20 10:45
 * @mark DeployController
 */
@RestController
@RequestMapping("/deploy")
public class DeployController {

    @Autowired
    private BusDeployInfoService busDeployInfoService;

    @PostMapping("/insertBusDeploy")
    public ResultPo insertBusDeployInfo(@RequestBody BusDeployInfoDto busDeployInfoDto) {
        Integer count = busDeployInfoService.insert(busDeployInfoDto);

        ResultPo resultPo = new ResultPo();
        resultPo.put("result", count > 0 ? "success" : "fail");
        return resultPo;

    }


    @PostMapping("/listBusDeployInfo")
    public ResultPo listBusDeployInfo(@RequestBody BusDeployInfoDto busDeployInfoDto,Integer pageNum,Integer pageSize) {

        List<BusDeployInfoDto> busDeployInfoDtos = busDeployInfoService.listBusDeployInfo(busDeployInfoDto,pageNum,pageSize);
        ResultPo resultPo = new ResultPo();
        resultPo.put("list", busDeployInfoDtos);
        return resultPo;

    }


}

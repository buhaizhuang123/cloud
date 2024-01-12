package com.bu.deploy.controller;

import com.bu.deploy.dto.DeploymentDto;
import com.bu.deploy.dto.FileDto;
import com.bu.deploy.service.DeploymentService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/10/31 14:26
 * @mark ProcessController
 */
@RestController
@RequestMapping("/deployment")
public class DeploymentController {

    private Logger log = LoggerFactory.getLogger(DeploymentController.class);


    @Autowired
    private DeploymentService deploymentService;

    @RequestMapping("/create")
    public boolean createDeployment(@RequestParam("name") String name, @RequestParam("filePath") String fileName) {

        try {
            deploymentService.createDeployment(name, fileName);
        } catch (Exception e) {
            log.error(" 流程新增失败:{} ", e);
            return false;
        }
        return true;
    }


    @RequestMapping("/list")
    @ResponseBody
    public PageInfo<DeploymentDto> listDeployment(Integer pageNum, Integer pageSize) {
        RowBounds rowBounds = new RowBounds(pageNum, pageSize);
        return deploymentService.listDeployment(rowBounds);
    }

    @DeleteMapping("/delete")
    public boolean deleteDeployment(String id) {
        return deploymentService.deleteDeployment(id);
    }

    @RequestMapping("/suspend")
    public boolean suspend(String id) {
        return deploymentService.suspendDeployment(id);
    }


    @RequestMapping("/active")
    public boolean active(String id) {
        return deploymentService.activeDeployment(id);
    }

    @RequestMapping("listFiles")
    public List<FileDto> listFiles() {
        String path = this.getClass().getResource("/").getPath();
        File file = new File(path + "processes");
        File[] files = file.listFiles();
        return Arrays.stream(files)
                .map(i -> {
                    FileDto fileDto = new FileDto();
                    fileDto.setFileName(i.getName());
                    return fileDto;
                })
                .collect(Collectors.toList());
    }

}

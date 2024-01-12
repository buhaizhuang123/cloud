package com.bu.file.controller;

import com.bu.common.po.ResultPo;
import com.bu.file.service.DeployFileService;
import com.bu.file.vo.UploadXmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2023/6/16 16:38
 * @mark UploadDeployController
 */

@RequestMapping("deploy")
@RestController
public class UploadDeployController {

    @Autowired
    private DeployFileService deployFileService;


    @PostMapping("upload")
    public ResultPo upload(@RequestBody UploadXmlVO uploadXmlVO) {


        deployFileService.insertDeployFile(uploadXmlVO);


        ResultPo resultPo = new ResultPo();
        resultPo.put("result", "上传成功");
        return resultPo;
    }


}

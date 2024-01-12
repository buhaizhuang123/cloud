package com.bu.file.service.impl;

import com.bu.file.dao.DeployFileDao;
import com.bu.file.dto.DeployFileDo;
import com.bu.file.service.DeployFileService;
import com.bu.file.vo.UploadXmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2023/6/16 17:31
 * @mark DeployFileServiceImpl
 */
@Service
public class DeployFileServiceImpl implements DeployFileService {

    @Autowired
    private DeployFileDao deployFileDao;

    /**
     * @param uploadXmlVO 文件上传对象
     * @return 插入部署流程对象信息
     */
    @Override
    public Integer insertDeployFile(UploadXmlVO uploadXmlVO) {
        String path = "/Applications/tools/wd/cloud/flowable-sping/src/main/resource/processes";
        String name = uploadXmlVO.getProcess().getName();
        String deployName = path + "/" + name + ".bpmn20" + ".xml";
        String xml = uploadXmlVO.getXml();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(deployName);
            fileOutputStream.write(xml.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DeployFileDo deployFileDo = new DeployFileDo();
        deployFileDo.setId(UUID.randomUUID().toString());
        deployFileDo.setXmlContent(xml);
        deployFileDo.setXmlPath(deployName);
        deployFileDo.setProcessName(name);
        return deployFileDao.insert(deployFileDo);
    }
}

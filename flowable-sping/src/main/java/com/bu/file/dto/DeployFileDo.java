package com.bu.file.dto;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/6/16 17:24
 * @mark DeployFileDo
 */
@Data
public class DeployFileDo {


    private String id;

    private String processName;

    private String xmlPath;

    private String xmlContent;

}

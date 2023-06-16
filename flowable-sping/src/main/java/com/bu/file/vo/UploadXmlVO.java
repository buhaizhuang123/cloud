package com.bu.file.vo;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/6/16 16:56
 * @mark UploadXmlVO
 */
@Data
public class UploadXmlVO {

    private Process process;

    private String svg;

    private String xml;



    @Data
    public class Process {
        private String category;

        private String id;

        private String name;

    }



}

package com.cloud.hp.service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/9/3 08:15
 * @mark HpFileService hadoop 文件操作
 */
public interface HpFileService {

    List<String> listDir(String path);


    List<String> listFile(String path);

    void readFile(String filePath);

    void downloadFromHp(String filePath, String path);

    void uploadFile(String localPath, String hadoopPath);


}

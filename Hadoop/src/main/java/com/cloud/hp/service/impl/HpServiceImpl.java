package com.cloud.hp.service.impl;

import com.cloud.hp.service.HpFileService;
import com.cloud.utils.HpUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/9/3 08:16
 * @mark HpServiceImpl
 */
public class HpServiceImpl implements HpFileService {

    private Logger logger = LoggerFactory.getLogger(HpServiceImpl.class);

    private FileSystem fileSystem = HpUtils.getFileSystem();

    /**
     * @author haizhuang.bu
     * @date 08:35 2022/9/3
     * @function listDir 目录查询
     */
    @Override
    public List<String> listDir(String path) {
        try {
            RemoteIterator<FileStatus> files = fileSystem.listStatusIterator(new Path(path));
            List<String> list = new ArrayList<>();
            while (files.hasNext()) {
                FileStatus fileStatus = files.next();
                if (fileStatus.isDirectory()) {
                    list.add(fileStatus.getPath().getName());
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author haizhuang.bu
     * @date 08:36 2022/9/3
     * @function 文件查询
     */
    @Override
    public List<String> listFile(String path) {
        try {
            ArrayList<String> list = new ArrayList<>();
            RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path(path), true);
            while (listFiles.hasNext()) {
                LocatedFileStatus fileStatus = listFiles.next();
                if (fileStatus.isFile()) {
                    list.add(fileStatus.getPath().getName());
                }
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author haizhuang.bu
     * @date 15:08 2022/9/3
     * @function 读取文件信息
     */
    @Override
    public void readFile(String filePath) {
        FSDataInputStream fsDataInputStream = null;
        try {
            System.out.println("===== 读取文件 =======");
            fsDataInputStream = fileSystem.open(new Path(filePath));
            System.out.println(fsDataInputStream.readLine());
            IOUtils.copyBytes(fsDataInputStream, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(fsDataInputStream);
        }
    }

    /**
     * @author haizhuang.bu
     * @date 15:07 2022/9/3
     * @function 文件下载
     */
    @Override
    public void downloadFromHp(String filePath, String path) {
        String ol = this.getClass().getResource("/").getPath();
        try {
            System.out.println(ol + path);
            fileSystem.copyToLocalFile(new Path(filePath), new Path(ol + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param hadoopPath hadoop文件地址
     * @param localPath  本地文件地址
     * @author haizhuang.bu
     * @date 15:10 2022/9/3
     * @function 提交文件
     */
    @Override
    public void uploadFile(String localPath, String hadoopPath) {
        logger.info("================ 文件上传开始localPath: {} =================", localPath);
        try {
            fileSystem.moveFromLocalFile(new Path(localPath), new Path(hadoopPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("================ 文件上传结束hadoopPath: {} =================", hadoopPath);
    }


    public static void main(String[] args) {
        HpServiceImpl hpService = new HpServiceImpl();
        hpService.readFile("/123.txt");
        List<String> list = hpService.listDir("/");
        System.out.println("list = " + list);
        List<String> file = hpService.listFile("/");
        System.out.println("file = " + file);
        hpService.downloadFromHp("/123.txt", "/txt");

        hpService.uploadFile("/Applications/tools/wd/cloud/Hadoop/target/classes//txt", "/tx");

    }
}

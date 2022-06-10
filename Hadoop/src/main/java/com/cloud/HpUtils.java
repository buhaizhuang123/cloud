package com.cloud;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/3/31 15:47
 * @mark HpUtils
 */
public class HpUtils {

    private static Configuration conf;
    private static FileSystem fileSystem;

    static {
        conf = new Configuration();
        System.setProperty("hadoop.home.dir", "/usr/local/Cellar/hadoop/3.3.2/");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.default.name", "hdfs://localhost:9000");
        getFileSystem();
    }

    public static FileSystem getFileSystem() {
        try {
            fileSystem = FileSystem.get(conf);
            return fileSystem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }


    public static Configuration getConf() {
        return conf;
    }

}

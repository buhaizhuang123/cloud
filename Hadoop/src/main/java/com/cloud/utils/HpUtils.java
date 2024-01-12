package com.cloud.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
        System.setProperty("HADOOP_USER_NAME", "root");
        System.setProperty("hadoop.home.dir", "/usr/local/Cellar/hadoop/3.3.2/");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("fs.default.name", "hdfs://hadoop01:9000");
        conf.set("dfs.client.use.datanode.hostname", "true");
    }

    public static FileSystem getFileSystem() {
        try {
            URI uri = new URI("hdfs://hadoop01:9000");
            fileSystem = FileSystem.get(uri,conf,"root");
            return fileSystem;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }


    public static Configuration getConf() {
        return conf;
    }

    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = HpUtils.getFileSystem();
        RemoteIterator<LocatedFileStatus> listFiles = fileSystem.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            Path path = fileStatus.getPath();
            System.out.println("fileStatus.isDirectory() = " + fileStatus.isDirectory());
            System.out.println("path.getName() = " + path.getName());
        }
        fileSystem.close();
    }

}

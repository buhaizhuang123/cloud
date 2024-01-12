package com.cloud.chain.sys.thr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Timer;
import java.util.concurrent.*;

/**
 * @author haizhuangbu
 * @date 2022/7/22 15:55
 * @mark ThreadTs
 */
public class ThreadTs {


    public static void main(String[] args) throws IOException {

//        Executors.newSingleThreadExecutor() // 创建单一线程 同时只能有一条线程执行
//        Executors.newFixedThreadPool() // 创建固定线程
//        Executors.newCachedThreadPool() // 创建可回收线程
//        Executors.newScheduledThreadPool() // 创建定时任务线程
        FileInputStream inputStream = new FileInputStream("/ts.txt");

        FileChannel channel = inputStream.getChannel();
        // 内存映射 性能高与普通buffer
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);


    }

}

package com.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haizhuangbu
 * @date 2023/3/2 19:39
 * @mark Exe
 */
public class Exe {

    public static void main(String[] args) {

        // corePoolSize 核心线程数
        // maximumPoolSize 最大线程数
        // keepAliveTime 保存日期
        // queue 队列
        ThreadPoolExecutor pools = new ThreadPoolExecutor(2, 3, 2000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(3));

        AtomicInteger r = new AtomicInteger(0);

        for (int i = 0; i < 6; i++) {
            pools.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i = " + r.getAndAdd(1));
                }
            });
        }


        pools.shutdown();



    }


}

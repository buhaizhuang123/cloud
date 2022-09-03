package com.cloud.image.controller;

import java.util.concurrent.*;

/**
 * @author haizhuangbu
 * @date 2022/8/24 11:48
 * @mark Trh
 */
public class Trh {

    public static void main(String[] args) {

        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new SynchronousQueue<>(), discardOldestPolicy);
        



    }

}

package com.cloud.limit.service;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2023/4/7 21:27
 * @mark ZkService
 */
public interface ZkService {

    void createNode(String nodePath) throws IOException, InterruptedException, KeeperException;

}

package com.cloud.limit.service.impl;

import com.cloud.limit.service.ZkService;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author haizhuangbu
 * @date 2023/4/7 21:28
 * @mark ZkServiceImpl
 */
@Service
public class ZkServiceImpl implements ZkService {


    @Override
    public void createNode(String nodePath) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 15000, null);
        ArrayList<ACL> acls = ZooDefs.Ids.OPEN_ACL_UNSAFE;
        zooKeeper.create(nodePath, "hello world".getBytes(StandardCharsets.UTF_8), acls, CreateMode.PERSISTENT);
    }


}

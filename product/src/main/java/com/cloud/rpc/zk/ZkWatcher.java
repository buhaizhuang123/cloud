package com.cloud.rpc.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author haizhuangbu
 * @date 2022/8/17 09:33
 * @mark ZkWatcher
 */
public class ZkWatcher implements Watcher {





    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        System.out.println("state.getIntValue() = " + state.getIntValue());

    }
}

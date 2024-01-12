package com.cloud.limit.controller;

import com.cloud.limit.service.ZkService;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2023/4/7 21:26
 * @mark ZkController
 */
@RestController
@RequestMapping("/zk")
public class ZkController {

    @Autowired
    private ZkService zkService;

    @RequestMapping("/create")
    public String create(@RequestParam("path") String nodePath) {
        try {
            zkService.createNode(nodePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return "success";
    }

}

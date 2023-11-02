package com.bu.deploy.service;

/**
 * @author haizhuangbu
 * @date 2023/11/2 10:16
 * @mark TaskService
 */
public interface TaskRunService {
    Boolean claim(String id, String name);

}

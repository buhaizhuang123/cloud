package com.bu.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.bu.common.po.ResultPo;
import com.bu.common.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/6/13 20:14
 * @mark ShowController
 */
@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    private ShowService showService;

    /**
     * @param path 路径
     * @return 启动部署流程
     */
    @GetMapping("startDeployment")
    public ResultPo startDeployment(String path, String name) {
        return showService.createDeployment(path, name);
    }

    /**
     * @param deploymentId
     * @return 挂起流程
     */
    @GetMapping("suspendDeployment")
    public ResultPo suspendDeployment(String deploymentId) {
        return showService.suspendDeployment(deploymentId);
    }

    /**
     * @param deploymentId
     * @return 唤醒流程
     */
    @GetMapping("activeDeployment")
    public ResultPo activeDeployment(String deploymentId) {
        return showService.activeDeployment(deploymentId);
    }

    /**
     * @param deploymentId
     * @return 删除流程
     */
    @GetMapping("deleteDeployment")
    public ResultPo deleteDeployment(String deploymentId) {
        return showService.deleteDeployment(deploymentId);
    }

    @GetMapping("queryDeployment")
    public ResultPo queryDeployment(String deploymentId) {
        return showService.queryDeployment(deploymentId);
    }


    /**
     * @return 启动流程实例
     */
    @GetMapping("startProcess")
    public JSONObject startProcess(String processId) {
        return showService.startProcess(processId);
    }

    @GetMapping("queryProcess")
    public ResultPo queryProcess(String processId) {
        return showService.queryProcessStatus(processId);
    }

    /**
     * @param processId
     * @return 挂起流程实例
     */
    @GetMapping("suspendProcess")
    public ResultPo suspendProcess(String processId) {
        return showService.suspendProcess(processId);
    }

    /**
     * @param processId
     * @return 唤醒流程实例
     */
    @GetMapping("activeProcess")
    public ResultPo activeProcess(String processId) {
        return showService.reviveProcess(processId);
    }

    /**
     * @param processId
     * @return 删除流程实例
     */
    @GetMapping("deleteProcess")
    public ResultPo deleteProcess(String processId, String userId) {
        return showService.deleteProcess(processId, userId);
    }

    /**
     * @param processId
     * @return 查询流程实例 流转节点
     */
    @GetMapping("queryExecution")
    public ResultPo queryProcessNode(String processId) {
        return showService.queryExecution(processId);
    }

    @GetMapping("nextTask")
    public ResultPo queryNextTaskInfo(String processId) {
        return showService.queryTaskByProcessId(processId);
    }

    @GetMapping("claim")
    public ResultPo claim(String taskId, String username) {
        return showService.claim(taskId, username);
    }

    @GetMapping("queryTaskInfo")
    public ResultPo queryTaskInfo(String taskId) {
        return showService.queryHistoryTaskInfo(taskId);
    }

    @GetMapping("queryTaskByName")
    public ResultPo queryTaskByName(String name) {
        return showService.queryTaskInfoByName(name);
    }

    @GetMapping("taskList")
    public ResultPo queryTaskList() {
        return showService.queryTaskInfoByTime();
    }

    @GetMapping("complete")
    public ResultPo complete(String taskId, String username) {
        return showService.completeTask(taskId, username);
    }

    @GetMapping("waitTask")
    public ResultPo waitTask(String userId) {
        return showService.queryUserTodoTasks(userId);
    }

    @GetMapping("finished")
    public ResultPo queryFinishedTask() {
        return showService.queryFinishedProcess();
    }

    @GetMapping("rejectLast")
    public ResultPo rejectLast(String taskId) {
        return showService.rejectProcessNode(taskId);
    }

    @GetMapping("listProcess")
    public ResultPo queryProcessList() {
        return showService.queryProcessList();
    }

    @GetMapping("listDeploys")
    public ResultPo listDeploys(int pageNum, int pageSize) {
        return showService.listDeploys(pageNum, pageSize);
    }


}

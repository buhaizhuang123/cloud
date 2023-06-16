package com.bu.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.bu.common.po.ResultPo;
import com.bu.common.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("startDeployment")
    public ResultPo startDeployment(String path) {
        return showService.createDeployment(path);
    }

    /**
     * @param deploymentId
     * @return 挂起流程
     */
    @RequestMapping("suspendDeployment")
    public ResultPo suspendDeployment(String deploymentId) {
        return showService.suspendDeployment(deploymentId);
    }

    /**
     * @param deploymentId
     * @return 唤醒流程
     */
    @RequestMapping("activeDeployment")
    public ResultPo activeDeployment(String deploymentId) {
        return showService.activeDeployment(deploymentId);
    }

    /**
     * @param deploymentId
     * @return 删除流程
     */
    @RequestMapping("deleteDeployment")
    public ResultPo deleteDeployment(String deploymentId) {
        return showService.deleteDeployment(deploymentId);
    }

    @RequestMapping("queryDeployment")
    public ResultPo queryDeployment(String deploymentId) {
        return showService.queryDeployment(deploymentId);
    }


    /**
     * @return 启动流程实例
     */
    @RequestMapping("startProcess")
    public JSONObject startProcess(String processId) {
        return showService.startProcess(processId);
    }

    @RequestMapping("queryProcess")
    public ResultPo queryProcess(String processId) {
        return showService.queryProcessStatus(processId);
    }

    /**
     * @param processId
     * @return 挂起流程实例
     */
    @RequestMapping("suspendProcess")
    public ResultPo suspendProcess(String processId) {
        return showService.suspendProcess(processId);
    }

    /**
     * @param processId
     * @return 唤醒流程实例
     */
    @RequestMapping("activeProcess")
    public ResultPo activeProcess(String processId) {
        return showService.reviveProcess(processId);
    }

    /**
     * @param processId
     * @return 删除流程实例
     */
    @RequestMapping("deleteProcess")
    public ResultPo deleteProcess(String processId, String userId) {
        return showService.deleteProcess(processId, userId);
    }

    /**
     * @param processId
     * @return 查询流程实例 流转节点
     */
    @RequestMapping("queryExecution")
    public ResultPo queryProcessNode(String processId) {
        return showService.queryExecution(processId);
    }

    @RequestMapping("nextTask")
    public ResultPo queryNextTaskInfo(String processId) {
        return showService.queryTaskByProcessId(processId);
    }

    @RequestMapping("claim")
    public ResultPo claim(String taskId, String username) {
        return showService.claim(taskId, username);
    }

    @RequestMapping("queryTaskInfo")
    public ResultPo queryTaskInfo(String taskId) {
        return showService.queryHistoryTaskInfo(taskId);
    }

    @RequestMapping("queryTaskByName")
    public ResultPo queryTaskByName(String name) {
        return showService.queryTaskInfoByName(name);
    }

    @RequestMapping("taskList")
    public ResultPo queryTaskList() {
        return showService.queryTaskInfoByTime();
    }

    @RequestMapping("complete")
    public ResultPo complete(String taskId, String username) {
        return showService.completeTask(taskId, username);
    }

    @RequestMapping("waitTask")
    public ResultPo waitTask(String userId) {
        return showService.queryUserTodoTasks(userId);
    }

    @RequestMapping("finished")
    public ResultPo queryFinishedTask() {
        return showService.queryFinishedProcess();
    }

    @RequestMapping("rejectLast")
    public ResultPo rejectLast(String taskId) {
        return showService.rejectProcessNode(taskId);
    }

    @RequestMapping("listProcess")
    public ResultPo queryProcessList() {
        return showService.queryProcessList();
    }

}

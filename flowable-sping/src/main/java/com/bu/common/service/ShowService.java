package com.bu.common.service;

import com.alibaba.fastjson.JSONObject;
import com.bu.common.po.ResultPo;
import org.flowable.engine.history.HistoricDetail;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;

import java.util.List;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2023/6/13 18:02
 * @mark ShowServic
 */
public interface ShowService {

    // 发起流程实例
    JSONObject startProcess(String process, Map<String, Object> map);

    // 挂起流程实例
    ResultPo suspendProcess(String processId);

    // 查询流程流转节点
    ResultPo queryExecution(String processId);


    // 查询流程执行历史
    ResultPo processHistroy(String processId);


    // 等待触发
    ResultPo trigger(String processId);


    List<Task> getTasksByName(String tasName);


    List<HistoricDetail> listHis(String processId);


    void startProcess(String process, String assignee);

    ResultPo deleteProcess(String processId, String userId);

    List<HistoricProcessInstance> getTasksById(String taskId);


    ProcessDefinition listProcess(String id);

    // 唤醒
    ResultPo reviveProcess(String processId);

    // 流程部署
    ResultPo createDeployment(String path,String name);


    // 删除部署
    ResultPo deleteDeployment(String deployId);

    // 挂起部署
    ResultPo suspendDeployment(String deployId);

    // 唤醒部署
    ResultPo activeDeployment(String deployId);


    // 查询待办任务
    ResultPo queryUserTodoTasks(String userId);

    // 领取任务
    ResultPo claim(String taskId, String userId);


    // 完成任务
    ResultPo finishedTask(String taskId);


    // 查询第一个任务
    ResultPo queryTaskByProcessId(String processId);

    // 添加任务与办理人关系
    ResultPo addUserToTask(String taskId, String username);

    // 完成任务
    ResultPo completeTask(String taskId, String name);

    // 查询历史流程实例信息
    ResultPo queryHistoryProcessInfo(String processId);

    // 查询历史任务实例
    ResultPo queryHistoryTaskInfo(String taskId);


    public ResultPo queryDeployment(String deployId);

    // 流程实例信息
    public ResultPo queryProcessStatus(String processId);

    // byname
    ResultPo queryTaskInfoByName(String name);


    // 日期范围内的任务
    ResultPo queryTaskInfoByTime();

    // 查询所有已经完成流程实例
    ResultPo queryFinishedProcess();

    // 退回指定节点
    ResultPo rejectProcessNode(String taskId);


    // 删除task任务
    ResultPo deleteTask(String taskId);


    // 获取所有流程信息
    ResultPo queryProcessList();

    ResultPo listDeploys(int pageNum, int pageSize);

    ResultPo sendInfoToTask(String processId, Map<String,Object> params);


}

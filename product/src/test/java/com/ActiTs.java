package com;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/6/13 11:20
 * @mark ActiTs
 */
@Slf4j
public class ActiTs {


    // repositoryService
    // runtimeService
    // taskService
    // historyService
    public static void main(String[] args) {
        // 获取工作流引擎
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        // 获取流程存储服务
        RepositoryService repositoryService = engine.getRepositoryService();
        // 获取运行时服务
        RuntimeService runtimeService = engine.getRuntimeService();
        // 获取流程任务
        TaskService taskService = engine.getTaskService();
        // 部署流程
        repositoryService.createDeployment().addClasspathResource("process/Worker.bpmn20.xml").deploy();
        // 启动流程
        runtimeService.startProcessInstanceByKey("Worker");
        // 查询第一个任务
        List<Task> task1s = taskService.createTaskQuery().taskName("张三").list();
        Task task1 = task1s.get(0);
        log.info("taskId:{},taskName:{} ", task1.getId(), task1.getName());
        // 完成第一个任务
        taskService.complete(task1.getId());
        List<Task> task2s = taskService.createTaskQuery().taskName("李四").list();
        Task task2 = task2s.get(0);
        log.info("taskId:{},taskName:{} ", task2.getId(), task2.getName());
        taskService.complete(task2.getId());
        // 查询任务数
        long taskNum = taskService.createTaskQuery().count();
        log.info("流程结束、剩余任务个数:{}", taskNum);
        // 关闭工作流引擎
        engine.close();

    }

}

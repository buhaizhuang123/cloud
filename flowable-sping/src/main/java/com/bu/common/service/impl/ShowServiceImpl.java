package com.bu.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bu.common.po.ResultPo;
import com.bu.common.service.ShowService;
import com.bu.common.to.WaitExeTaskTo;
import com.bu.deploy.dao.BusDeployInfoMapper;
import com.bu.deploy.dto.BusDeployInfoDto;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricDetail;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/6/13 20:11
 * @mark ShowServiceImpl
 */
@Service
public class ShowServiceImpl implements ShowService {
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;
    @Resource
    private HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private BusDeployInfoMapper busDeployInfoMapper;


    @Override
    public JSONObject startProcess(String process, Map<String, Object> map) {

        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .variable("param", map)
                .processDefinitionId(process)
                .name(map.get("name").toString()).start();
        String id = processInstance.getId();
        String startUserId = processInstance.getStartUserId();
        JSONObject reson = new JSONObject();
        reson.put("id", id);
        reson.put("userId", startUserId);
        return reson;
    }

    @Override
    public ResultPo suspendProcess(String processId) {
        runtimeService.suspendProcessInstanceById(processId);
        return queryProcessStatus(processId);

    }

    @Override
    public ResultPo queryExecution(String processId) {
        Execution execution = runtimeService.createExecutionQuery().executionId(processId).singleResult();
        ResultPo resultPo = new ResultPo();
        resultPo.put("id", execution.getId()).put("name", execution.getName()).put("super", execution.getSuperExecutionId()).put("parent", execution.getParentId()).put("processId", execution.getProcessInstanceId()).put("root", execution.getRootProcessInstanceId());
        return resultPo;
    }

    @Override
    public ResultPo processHistroy(String processId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        historyService.createHistoricTaskInstanceQuery();
        ResultPo resultPo = new ResultPo();
        resultPo.put("id", historicProcessInstance.getId()).put("name", historicProcessInstance.getName()).put("businessKey", historicProcessInstance.getBusinessKey()).put("deploymentId", historicProcessInstance.getDeploymentId()).put("startId", historicProcessInstance.getStartActivityId()).put("startTime", historicProcessInstance.getStartTime()).put("startUserId", historicProcessInstance.getStartUserId()).put("endTime", historicProcessInstance.getEndTime()).put("endId", historicProcessInstance.getEndActivityId());
        return resultPo;
    }

    @Override
    public ResultPo trigger(String processId) {
        runtimeService.trigger(processId);
        return queryProcessStatus(processId);
    }

    @Override
    public List<Task> getTasksByName(String tas) {
        return taskService.createTaskQuery().taskName(tas).list();
    }


    public List<HistoricDetail> listHis(String processId) {
        return historyService.createHistoricDetailQuery().processInstanceId(processId).list();
    }

    @Override
    public void startProcess(String process, String assignee) {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("person", assignee);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(process, variables);

    }

    @Override
    public ResultPo deleteProcess(String processId, String userId) {
        runtimeService.deleteProcessInstance(processId, userId);
        ResultPo resultPo = new ResultPo();
        return resultPo.put("result", "删除成功");
    }

    @Override
    public List<HistoricProcessInstance> getTasksById(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getId()).list();
        return list;
    }

    public ProcessDefinition listProcess(String id) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();

        return processDefinition;
    }

    @Override
    public ResultPo reviveProcess(String processId) {
        runtimeService.activateProcessInstanceById(processId);
        return queryProcessStatus(processId);
    }

    @Override
    public ResultPo createDeployment(String path, String name) {
        Deployment deploy = repositoryService.createDeployment().name(name).addClasspathResource("processes/" + path).deploy();

        return queryDeployment(deploy.getId());
    }

    @Override
    public ResultPo deleteDeployment(String deployId) {
        repositoryService.deleteDeployment(deployId, true);
        return queryDeployment(deployId);
    }

    @Override
    public ResultPo suspendDeployment(String deployId) {
        repositoryService.suspendProcessDefinitionById(deployId);
        return queryDeployment(deployId);
    }

    @Override
    public ResultPo activeDeployment(String deployId) {
        repositoryService.activateProcessDefinitionById(deployId);
        return queryDeployment(deployId);
    }

    @Override
    public ResultPo queryUserTodoTasks(String userId, RowBounds rowBounds) {
        PageHelper.startPage(rowBounds.getOffset(), rowBounds.getLimit());
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).orderByTaskCreateTime().desc().list();
        PageInfo<Task> taskPageInfo = new PageInfo<>(list);
        ResultPo resultPo = new ResultPo();
        ArrayList<Object> res = new ArrayList<>();
        for (Task task : taskPageInfo.getList()) {
            Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
            WaitExeTaskTo waitExeTaskTo = new WaitExeTaskTo();
            BeanUtils.copyProperties(task, waitExeTaskTo);
            waitExeTaskTo.setProcessVariables(variables);
            String name = (String) variables.get("name");
            if (name != null) {
                BusDeployInfoDto busDeployInfoByName = busDeployInfoMapper.findBusDeployInfoByName(name);
                waitExeTaskTo.setBusDeployInfoDto(busDeployInfoByName);
            }
            waitExeTaskTo.setIsSuspend(task.isSuspended());
            res.add(waitExeTaskTo);
        }
        resultPo.put("list", res);
        resultPo.put("total", taskPageInfo.getTotal());
        return resultPo;
    }

    @Override
    public ResultPo claim(String taskId, String userId) {

        taskService.claim(taskId, userId);
        taskService.setVariable(taskId, "param", "ssxxx");
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        ResultPo resultPo1 = new ResultPo();
        ArrayList<Object> list1 = new ArrayList<>();
        for (Task task : list) {
            ResultPo resultPo = new ResultPo();

            resultPo.put("claimTime", task.getClaimTime()).put("name", task.getName()).put("assignee", task.getAssignee()).put("executionId", task.getExecutionId()).put("processId", task.getProcessDefinitionId()).put("createTime", task.getCreateTime());
            list1.add(resultPo);
        }
        return resultPo1.put("list", list1);
    }

    @Override
    public ResultPo finishedTask(String taskId) {
        taskService.complete(taskId);
        ResultPo resultPo = new ResultPo();
        return resultPo.put(taskId, "完成");
    }

    @Override
    public ResultPo queryTaskByProcessId(String processId) {
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processId).list();
        ResultPo resultPo1 = new ResultPo();
        ArrayList<Object> list = new ArrayList<>();
        for (Task task : tasks) {
            Object param = runtimeService.getVariable(processId, "param");
            ResultPo resultPo = new ResultPo();
            resultPo.put("claimTime", task.getClaimTime())
                    .put("v", task.getProcessVariables())
                    .put("name", task.getName())
                    .put("assignee", task.getAssignee())
                    .put("executionId", task.getExecutionId())
                    .put("processId", task.getProcessDefinitionId())
                    .put("createTime", task.getCreateTime())
                    .put("vis", task.getTaskLocalVariables())
                    .put("param", param)
                    .put("id", task.getId());
            list.add(resultPo);
        }

        return resultPo1.put("list", list);
    }

    @Override
    public ResultPo addUserToTask(String taskId, String username) {
        taskService.addCandidateUser(taskId, username);
        Task task = taskService.createTaskQuery().taskCandidateOrAssigned(username).singleResult();
        ResultPo resultPo = new ResultPo();
        resultPo.put("claimTime", task.getClaimTime()).put("name", task.getName()).put("assignee", task.getAssignee()).put("executionId", task.getExecutionId()).put("processId", task.getProcessDefinitionId()).put("createTime", task.getCreateTime());
        return resultPo;
    }

    @Override
    public ResultPo completeTask(String taskId, String name) {
        ResultPo resultPo = new ResultPo();
        // 查询task是否存在
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null || !task.getAssignee().equals(name)) {
            resultPo.put("result", "任务不存在、或者已被其他人处理完成");
            return resultPo;
        }

        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(taskId);
        IdentityLink identity = null;
        for (IdentityLink identityLink : identityLinksForTask) {
            if (identityLink.getUserId().equals(name)) {
                identity = identityLink;
            }
        }

        if (identity == null) {
            resultPo.put("result", name + "没有查看当前任务的权限");
            return resultPo;
        }

        if (identity.getType().equals("candidate")) {

            taskService.claim(taskId, name);
            HashMap<String, Object> vali = new HashMap<>();
            vali.put("审批意见", "审批通过");
            vali.put("flag", false);
            taskService.complete(taskId, vali);
            resultPo.put("result", "任务处理完成");
        } else {
            taskService.claim(taskId, name);
            HashMap<String, Object> vali = new HashMap<>();
            vali.put("审批意见", "审批通过");
            vali.put("flag", true);
            taskService.complete(taskId, vali);
            resultPo.put("result", "任务处理完成");
        }


        return resultPo;
    }

    @Override
    public ResultPo queryHistoryProcessInfo(String processId) {

        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).orderByProcessInstanceStartTime().asc().list();

        ResultPo resultPo = new ResultPo();
        ArrayList<Object> list1 = new ArrayList<>();
        for (HistoricProcessInstance historicProcessInstance : list) {
            ResultPo resultPo1 = new ResultPo();
            resultPo1.put("name", historicProcessInstance.getName()).put("startUser", historicProcessInstance.getStartUserId()).put("deployment", historicProcessInstance.getDeploymentId()).put("version", historicProcessInstance.getProcessDefinitionVersion()).put("endTime", historicProcessInstance.getEndTime()).put("startDate", historicProcessInstance.getStartTime()).put("tenantId", historicProcessInstance.getTenantId());
            list1.add(resultPo1);
        }
        return resultPo.put("list", list1);
    }

    @Override
    public ResultPo queryHistoryTaskInfo(String taskId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskId(taskId).orderByTaskCreateTime().asc().list();


        ResultPo resultPo = new ResultPo();
        ArrayList<Object> list1 = new ArrayList<>();
        for (HistoricTaskInstance historicTaskInstance : list) {

            ResultPo resultPo1 = new ResultPo();

            resultPo1.put("startTime", historicTaskInstance.getStartTime()).put("claimTime", historicTaskInstance.getClaimTime()).put("assignee", historicTaskInstance.getAssignee()).put("endTime", historicTaskInstance.getEndTime()).put("tenantId", historicTaskInstance.getTenantId()).put("vis", historicTaskInstance.getProcessVariables()).put("id", historicTaskInstance.getId());
            list1.add(resultPo1);
        }


        return resultPo.put("list", list1);
    }

    public ResultPo queryProcessStatus(String processId) {
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().processInstanceId(processId);
        ProcessInstance processInstance = processInstanceQuery.singleResult();
        ResultPo resultPo = new ResultPo();
        resultPo.put("processId", processInstance.getId()).put("suspended", processInstance.isSuspended()).put("variables", processInstance.getProcessVariables()).put("start", processInstance.getStartTime()).put("end", processInstance.isEnded());
        return resultPo;
    }

    @Override
    public ResultPo queryTaskInfoByName(String name) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).list();
        ResultPo resultPo1 = new ResultPo();
        asmTask(list, resultPo1);
        return resultPo1;
    }

    @Override
    public ResultPo queryDeployment(String deployId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        if (processDefinition == null) return null;
        ResultPo resultPo = new ResultPo();
        resultPo.put("deploymentId", processDefinition.getDeploymentId()).put("suspended", processDefinition.isSuspended()).put("name", processDefinition.getName()).put("version", processDefinition.getVersion()).put("tenantId", processDefinition.getTenantId()).put("key", processDefinition.getKey()).put("root", processDefinition.getDerivedFromRoot()).put("id", processDefinition.getId());
        return resultPo;
    }


    @Override
    public ResultPo queryTaskInfoByTime() {
        List<Task> list = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();
        ResultPo resultPo = new ResultPo();
        asmTask(list, resultPo);
        return resultPo;
    }

    @Override
    public ResultPo queryFinishedProcess() {


        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().finished().orderByProcessInstanceStartTime().asc().list();


        ResultPo resultPo = new ResultPo();

        List<ResultPo> collect = list.stream().map(i -> {

            ResultPo resultPo1 = new ResultPo();
            resultPo1.put("name", i.getName()).put("startTime", i.getStartTime()).put("endTime", i.getEndTime()).put("startUserId", i.getStartUserId()).put("businessKey", i.getBusinessKey()).put("processName", i.getProcessDefinitionName()).put("processId", i.getId()).put("deployment", i.getDeploymentId());
            return resultPo1;
        }).collect(Collectors.toList());

        return resultPo.put("list", collect);
    }

    @Override
    public ResultPo rejectProcessNode(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId()).orderByTaskCreateTime().asc().list();
        ResultPo resultPo = new ResultPo();
        if (CollectionUtils.isEmpty(list)) return resultPo.put("result", "无历史节点退回失败!!!");
        HistoricTaskInstance historicTaskInstance = list.get(0);
        // 替换节点
        System.out.println("task.getTaskDefinitionKey() = " + task.getTaskDefinitionKey());
        runtimeService.createChangeActivityStateBuilder().processInstanceId(historicTaskInstance.getProcessInstanceId()).moveActivityIdTo(task.getTaskDefinitionKey(), historicTaskInstance.getTaskDefinitionId()).changeState();

        String taskDefinitionId = historicTaskInstance.getTaskDefinitionId();
        System.out.println("taskDefinitionId = " + taskDefinitionId);
        resultPo.put("result", "节点退回成功!!!");

        return resultPo;
    }

    @Override
    public ResultPo deleteTask(String taskId) {
        taskService.deleteTask(taskId, true);
        return null;
    }

    @Override
    public ResultPo queryProcessList() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().orderByProcessDefinitionId().asc().list();
        ResultPo resultPo1 = new ResultPo();
        List<ResultPo> list1 = list.stream().map(i -> {
            ResultPo resultPo = new ResultPo();
            resultPo.put("startTime", i.getStartTime()).put("businessKey", i.getBusinessKey()).put("startUser", i.getStartUserId()).put("name", i.getName()).put("processDefinitionId", i.getProcessDefinitionId()).put("id", i.getId()).put("vis", i.getProcessVariables()).put("activityId", i.getActivityId());
            return resultPo;
        }).collect(Collectors.toList());

        return resultPo1.put("list", list1);
    }

    @Override
    public ResultPo listDeploys(int pageNum, int pageSize) {
        ResultPo resultPo = new ResultPo();
        ListIterator<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().listPage(pageNum, pageSize).listIterator();
        List<JSONObject> list1 = new ArrayList<>();
        while (list.hasNext()) {
            Deployment deployment = list.next();
            JSONObject jsonObject = asmDeploy(deployment);
            list1.add(jsonObject);
        }
        resultPo.put("list", list1);
        return resultPo;
    }

    @Override
    public ResultPo sendInfoToTask(String processId, Map<String, Object> params) {

        return null;
    }

    @Override
    public ResultPo queryHistoryProcess(String processId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().processInstanceId(processId).orderByTaskCreateTime().asc().list();

        ResultPo resultPo1 = new ResultPo();
        List<JSONObject> list1 = list.stream().map(i -> {

            ResultPo resultPo = new ResultPo();
            resultPo.put("assignee", i.getAssignee()).put("claimTime", i.getClaimTime()).put("id", i.getId()).put("name", i.getName()).put("vis", i.getProcessVariables());
            return resultPo.getData();
        }).collect(Collectors.toList());
        resultPo1.put("list", list1);
        return resultPo1;
    }


    public void asmTask(List<Task> tasks, ResultPo resultPo1) {

        List<ResultPo> list = tasks.stream().map(i -> {
            ResultPo resultPo = new ResultPo();
            resultPo.put("name", i.getName()).put("id", i.getId()).put("assignee", i.getAssignee()).put("owner", i.getOwner()).put("time", i.getClaimTime()).put("vis", i.getTaskLocalVariables()).put("processId", i.getProcessDefinitionId()).put("taskId", i.getTaskDefinitionId());
            return resultPo;
        }).collect(Collectors.toList());
        resultPo1.put("list", list);
    }

    public JSONObject asmDeploy(Deployment deployment) {
        ResultPo resultPo = new ResultPo();

        resultPo.put("name", deployment.getName()).put("deploymentTime", deployment.getDeploymentTime()).put("isNew", deployment.isNew()).put("category", deployment.getCategory()).put("id", deployment.getId());
        return resultPo.getData();
    }


    public void identity() {

    }

}

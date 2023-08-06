package com.cloud.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.common.SComCde;
import com.cloud.sys.dao.DeptMapper;
import com.cloud.sys.dto.SDeptDo;
import com.cloud.sys.service.DeptService;
import com.cloud.sys.service.FlowableService;
import com.cloud.sys.to.FlowableIdentityGroupInfo;
import com.cloud.sys.vo.DeptVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:08
 * @mark DeptServiceImpl
 */
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private FlowableService flowableService;

    @Transactional
    @Override
    public void saveDept(SDeptDo sDeptDo) {
        sDeptDo.setCreateTime(new Date());
        sDeptDo.setUpdateTime(new Date());
        sDeptDo.setIsRunning("0");
        sDeptDo.setDeptId(UUID.randomUUID().toString());
        deptMapper.save(sDeptDo);
        log.info("保存机构信息成功");
        // todo 通知工作流系统 创建分组信息 group
        FlowableIdentityGroupInfo flowableIdentityGroupInfo = new FlowableIdentityGroupInfo();
        flowableIdentityGroupInfo.setGroupId(sDeptDo.getDeptCode()).setGroupName(sDeptDo.getDeptName()).setGroupType(sDeptDo.getDeptType());
        flowableService.saveIdentityGroup(flowableIdentityGroupInfo);
        log.info("通知工作流引擎成功");
    }

    @Override
    public List<SDeptDo> listDept(DeptVo deptVo) {
        RowBounds rowBounds = new RowBounds();
        if (deptVo != null) {
            rowBounds = new RowBounds(deptVo.getPageNum(), deptVo.getPageSize());
        }
        List<SDeptDo> list = deptMapper.listDept(deptVo, rowBounds);
        return list;
    }

    @Override
    public List<SComCde> listDistinctDept() {
        List<SComCde> list = deptMapper.list();
        return list;
    }
}

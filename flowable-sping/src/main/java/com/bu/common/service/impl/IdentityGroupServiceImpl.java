package com.bu.common.service.impl;

import com.bu.common.form.IdentityAddUserReq;
import com.bu.common.form.IdentityGroupFormReq;
import com.bu.common.service.IdentityGroupService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.IdentityServiceImpl;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.idm.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:20
 * @mark IdentityGroupServiceImpl
 */
@Service
@Slf4j
public class IdentityGroupServiceImpl implements IdentityGroupService {

    @Autowired
    private IdentityService identityService;


    @Override
    public void saveGroup(IdentityGroupFormReq identityGroupFormReq) {
        Group group = identityService.newGroup(identityGroupFormReq.getGroupId());
        group.setId(identityGroupFormReq.getGroupId());
        group.setName(identityGroupFormReq.getGroupName());
        group.setType(identityGroupFormReq.getGroupType());
        // 保存
        identityService.saveGroup(group);
        log.info("保存用户组信息成功");
    }

    @Override
    public void saveUser(IdentityAddUserReq identityAddUserReq) {
        User user = identityService.newUser(identityAddUserReq.getUserId());
        user.setId(identityAddUserReq.getUserId());
        user.setFirstName(identityAddUserReq.getUserName());
        user.setPassword(identityAddUserReq.getPassword());
        identityService.saveUser(user);
        identityService.createMembership(user.getId(), identityAddUserReq.getGroupId());
    }


    public List<Group> listGroup() {
        GroupQuery groupQuery = identityService.createGroupQuery().orderByGroupId();
        return groupQuery.list();
    }
}

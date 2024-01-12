package com.bu.deploy.service.impl;

import com.bu.deploy.dao.UsrIdentityMapper;
import com.bu.deploy.dto.GroupEntity;
import com.bu.deploy.dto.UserEntity;
import com.bu.deploy.service.IdentifyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2023/11/14 10:20
 * @mark IdentifyServiceImpl
 */
@Service
@Slf4j
public class IdentifyServiceImpl implements IdentifyService {

    @Autowired
    private IdentityService identityService;

    @Autowired
    private UsrIdentityMapper usrIdentityMapper;

    public void addUser(UserEntity userEntity) {
        User user = identityService.newUser(userEntity.getId());
        BeanUtils.copyProperties(userEntity, user);
        identityService.saveUser(user);
    }

    public List<UserEntity> list(Integer pageNum, Integer pageSize) {
        UserQuery userQuery = identityService.createUserQuery();
        List<User> list = userQuery.list();
        if (list != null) {

            return list.stream()
                    .map(i -> {
                        UserEntity userEntity = new UserEntity();
                        BeanUtils.copyProperties(i, userEntity);
                        return userEntity;
                    }).collect(Collectors.toList());


        }

        return null;

    }

    @Override
    public void addGroup(GroupEntity groupEntity) {
        Group group = identityService.newGroup(groupEntity.getId());
        BeanUtils.copyProperties(groupEntity, group);
        identityService.saveGroup(group);
    }

    public void relationGroup(String userId, String groupId) {

        identityService.createMembership(userId, groupId);
    }

    @Override
    public Boolean removeUserRelation(String userId) {
        identityService.deleteUser(userId);
        return true;
    }

    @Override
    public PageInfo<GroupEntity> listGroup(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GroupEntity> listGroup = usrIdentityMapper.listGroup();
        PageInfo<GroupEntity> groupEntityPageInfo = new PageInfo<>(listGroup);
        return groupEntityPageInfo;
    }

    @Override
    public Boolean deleteGroup(String groupId) {
        identityService.deleteGroup(groupId);
        return true;
    }

    @Override
    public List<GroupEntity> listGroup() {
        List<GroupEntity> listGroup = usrIdentityMapper.listGroup();
        return listGroup;
    }


}

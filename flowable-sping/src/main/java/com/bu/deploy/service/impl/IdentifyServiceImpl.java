package com.bu.deploy.service.impl;

import com.bu.deploy.dto.GroupEntity;
import com.bu.deploy.dto.UserEntity;
import com.bu.deploy.service.IdentifyService;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.flowable.idm.api.UserQuery;
import org.flowable.idm.engine.impl.persistence.entity.GroupEntityImpl;
import org.flowable.idm.engine.impl.persistence.entity.UserEntityImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addUser(UserEntity userEntity) {
        UserEntityImpl user = new UserEntityImpl();
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

        GroupEntityImpl group = new GroupEntityImpl();
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


}

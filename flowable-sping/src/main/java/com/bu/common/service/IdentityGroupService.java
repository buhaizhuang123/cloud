package com.bu.common.service;

import com.bu.common.form.IdentityAddUserReq;
import com.bu.common.form.IdentityGroupFormReq;
import org.flowable.idm.api.Group;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:19
 * @mark IdentityGroupService
 */
public interface IdentityGroupService {

    /**
     * @param identityGroupFormReq 用户组信息
     *                             保存用户组信息
     */
    void saveGroup(IdentityGroupFormReq identityGroupFormReq);


    void saveUser(IdentityAddUserReq identityAddUserReq);


    List<Group> listGroup();
}

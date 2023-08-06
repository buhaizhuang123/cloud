package com.bu.common.controller;

import com.bu.common.form.IdentityAddUserReq;
import com.bu.common.form.IdentityGroupFormReq;
import com.bu.common.service.IdentityGroupService;
import org.flowable.idm.api.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:16
 * @mark IdentityController
 */
@RestController
@RequestMapping("/identity")
public class IdentityController {

    @Autowired
    private IdentityGroupService identityGroupService;

    @RequestMapping("/saveGroup")
    public void saveGroup(@RequestBody IdentityGroupFormReq identityGroupFormReq) {
        identityGroupService.saveGroup(identityGroupFormReq);
    }

    @RequestMapping("/saveUser")
    public void saveUser(@RequestBody IdentityAddUserReq identityAddUserReq) {
        identityGroupService.saveUser(identityAddUserReq);
    }

    @RequestMapping("/listGroup")
    public List<Group> listGroup(){
        return identityGroupService.listGroup();
    }

}

package com.cloud.usr.controller;

import com.cloud.usr.dto.Usr;
import com.cloud.usr.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/29 09:42
 * @mark UsrController
 */
@RequestMapping("usr")
@RestController
public class UsrController {

    @Autowired
    private UsrService usrService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<Usr> listUsr(){
        List<Usr> usrs = usrService.listUsr();
        return usrs;
    }

}

package com.cloud.usr.controller;

import com.cloud.shop.dto.Page;
import com.cloud.usr.dto.Usr;
import com.cloud.usr.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "list")
    public List<Usr> listUsr(@RequestBody Page page){
        List<Usr> usrs = usrService.listUsr(page);
        return usrs;
    }

}

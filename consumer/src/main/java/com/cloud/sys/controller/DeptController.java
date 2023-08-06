package com.cloud.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.SComCde;
import com.cloud.sys.dto.SDeptDo;
import com.cloud.sys.service.DeptService;
import com.cloud.sys.vo.DeptVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:12
 * @mark DeptContrtoller
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;


    /**
     * @param sDeptDo 机构实体
     * @return 保存机构信息
     */
    @RequestMapping(value = "/saveDept", method = RequestMethod.POST)
    public String saveDept(@RequestBody SDeptDo sDeptDo) {
        deptService.saveDept(sDeptDo);
        return "SUCCESS";
    }


    @RequestMapping(value = "/listDept", method = RequestMethod.POST)
    public List<SDeptDo> listDept(@RequestBody(required = false) DeptVo deptVo) {
        return deptService.listDept(deptVo);
    }


    //listDistinctDept

    @RequestMapping(value = "/listDistinctDept", method = RequestMethod.POST)
    public List<SComCde> listDistinctDept() {
        return deptService.listDistinctDept();
    }
}

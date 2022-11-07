package com.cloud.limit.controller;

import com.cloud.limit.dao.LimitApplyDao;
import com.cloud.limit.dto.CreditLimitApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/11/4 17:47
 * @mark LimitController
 */
@RequestMapping("limit")
@RestController
public class LimitController {

    @Autowired
    private LimitApplyDao limitApplyDao;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public List<CreditLimitApply> find() {
        return limitApplyDao.find();
    }


}

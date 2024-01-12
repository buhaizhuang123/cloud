package com.cloud.limit.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.limit.LimitCustQueryVo;
import com.cloud.limit.dto.CreditLimit;
import com.cloud.limit.dto.CreditLimitApply;
import com.cloud.limit.service.CreditLimitApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2022/11/4 17:47
 * @mark LimitController
 */
@RequestMapping("limit")
@RestController
public class LimitController {

    @Autowired
    private CreditLimitApplyService creditLimitApplyService;

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public List<CreditLimitApply> find(@RequestBody(required = false) LimitCustQueryVo limitCustQueryVo) {
        return creditLimitApplyService.find(limitCustQueryVo);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveLimit(@RequestBody CreditLimit creditLimit) {
        return creditLimitApplyService.saveLimitApply(creditLimit).toString();
    }

    @RequestMapping(value = "/findByApplSeq", method = RequestMethod.GET)
    public CreditLimit findByApplSeq(@RequestParam("applSeq") String applSeq) {
        return creditLimitApplyService.findByApplSeq(applSeq);
    }

}

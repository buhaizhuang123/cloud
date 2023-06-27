package com.cloud.limit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.cust.dao.CustInfoMapper;
import com.cloud.cust.dao.SpouseInfoMapper;
import com.cloud.limit.LimitCustQueryVo;
import com.cloud.limit.dao.LimitApplyDao;
import com.cloud.limit.dto.CreditLimit;
import com.cloud.limit.dto.CreditLimitApply;
import com.cloud.limit.service.CreditLimitApplyService;
import com.cloud.service.FlowableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2022/11/8 08:58
 * @mark CreditLimitApplyServiceImpl
 */
@Service
@Slf4j
public class CreditLimitApplyServiceImpl implements CreditLimitApplyService {

    @Autowired
    private LimitApplyDao limitApplyDao;
    @Autowired
    private CustInfoMapper custInfoMapper;
    @Autowired
    private SpouseInfoMapper spouseInfoMapper;

    @Autowired
    private FlowableService flowableService;

    @Override
    public List<CreditLimitApply> find(LimitCustQueryVo limitCustQueryVo) {
        return limitApplyDao.find(limitCustQueryVo);
    }

    @Override
    @Transactional
    public Integer saveLimitApply(CreditLimit creditLimit) {
        String applSeq = "LIMIT" + UUID.randomUUID().toString().replaceAll("-", "");
        creditLimit.setApplSeq(applSeq);
        custInfoMapper.saveLimitApplyCustInfo(creditLimit);
        if (creditLimit.getSpouseInfo() != null) {
            log.info("---------- 开始保存配偶信息 ------------");
            spouseInfoMapper.save(creditLimit);
        }

        CreditLimitApply creditLimitApply = new CreditLimitApply();
        BeanUtils.copyProperties(creditLimit.getPersonalInfo(), creditLimitApply);
        creditLimitApply.setApplSeq(applSeq);
        creditLimitApply.setApplyDt(new Date());
        creditLimitApply.setLimitSts("0");
        Integer integer = limitApplyDao.saveLimitApply(creditLimitApply);
        Map map = JSONObject.parseObject(JSONObject.toJSONString(creditLimitApply), Map.class);
        map.put("name","授信申请");
        flowableService.startProcess("ck-001:27:135004",map);
        return integer;
    }

    @Override
    public CreditLimit findByApplSeq(String applSeq) {
        return custInfoMapper.findCreditLimitByApplSeq(applSeq);
    }
}

package com.cloud.limit.service.impl;

import com.cloud.cust.dao.CustInfoMapper;
import com.cloud.limit.LimitCustQueryVo;
import com.cloud.limit.dao.LimitApplyDao;
import com.cloud.limit.dto.CreditLimit;
import com.cloud.limit.dto.CreditLimitApply;
import com.cloud.limit.service.CreditLimitApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author haizhuangbu
 * @date 2022/11/8 08:58
 * @mark CreditLimitApplyServiceImpl
 */
@Service
public class CreditLimitApplyServiceImpl implements CreditLimitApplyService {

    @Autowired
    private LimitApplyDao limitApplyDao;
    @Autowired
    private CustInfoMapper custInfoMapper;


    @Override
    public List<CreditLimitApply> find(LimitCustQueryVo limitCustQueryVo) {
        return limitApplyDao.find(limitCustQueryVo);
    }

    @Override
    public Integer saveLimitApply(CreditLimit creditLimit) {
        String applSeq = "LIMIT" + UUID.randomUUID().toString().replaceAll("-","");
        creditLimit.setApplSeq(applSeq);
        custInfoMapper.saveLimitApplyCustInfo(creditLimit);
        CreditLimitApply creditLimitApply = new CreditLimitApply();
        BeanUtils.copyProperties(creditLimit.getPersonalInfo(), creditLimitApply);
        creditLimitApply.setApplSeq(applSeq);
        creditLimitApply.setApplyDt(new Date());
        creditLimitApply.setLimitSts("0");
        return limitApplyDao.saveLimitApply(creditLimitApply);
    }
}

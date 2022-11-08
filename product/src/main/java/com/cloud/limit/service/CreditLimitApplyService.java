package com.cloud.limit.service;

import com.cloud.limit.LimitCustQueryVo;
import com.cloud.limit.dto.CreditLimit;
import com.cloud.limit.dto.CreditLimitApply;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/11/8 08:56
 * @mark CreditLimitApplyService
 */
public interface CreditLimitApplyService {

    List<CreditLimitApply> find(LimitCustQueryVo limitCustQueryVo);


    Integer saveLimitApply(CreditLimit creditLimit);


}

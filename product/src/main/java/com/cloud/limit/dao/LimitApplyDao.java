package com.cloud.limit.dao;

import com.cloud.limit.LimitCustQueryVo;
import com.cloud.limit.dto.CreditLimitApply;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/11/4 17:50
 * @mark LimitApplyDao
 */
public interface LimitApplyDao {

    List<CreditLimitApply> find(LimitCustQueryVo limitCustQueryVo);

    Integer saveLimitApply(CreditLimitApply creditLimitApply);


}

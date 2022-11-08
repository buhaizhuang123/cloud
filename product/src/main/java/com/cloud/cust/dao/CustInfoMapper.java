package com.cloud.cust.dao;

import com.cloud.limit.dto.CreditLimit;

/**
 * @author haizhuangbu
 * @date 2022/11/7 14:25
 * @mark CustInfoMapper
 */
public interface CustInfoMapper {

    Integer saveLimitApplyCustInfo(CreditLimit creditLimit);
}

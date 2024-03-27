package com.cloud.person.service.impl;

import com.cloud.person.dao.S2Mapper;
import com.cloud.person.service.S2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author haizhuangbu
 * @date 2024/3/27 10:05
 * @mark S2ServiceImpl
 */
@Service
public class S2ServiceImpl implements S2Service {

    @Autowired
    private S2Mapper s2Mapper;


    @Override
    // 声明当前事务为 REQUIRED
//    @Transactional(propagation = Propagation.NESTED)
    public void insert(String data) {
        s2Mapper.insert(data);
    }
}

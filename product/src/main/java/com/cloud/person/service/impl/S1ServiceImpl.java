package com.cloud.person.service.impl;

import com.cloud.person.dao.S1Mapper;
import com.cloud.person.service.S1Service;
import com.cloud.person.service.S2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author haizhuangbu
 * @date 2024/3/27 09:58
 * @mark S1ServiceImpl
 */
@Service
public class S1ServiceImpl implements S1Service {

    @Autowired
    private S1Mapper s1Mapper;

    @Autowired
    private S2Service s2Service;

    @Autowired
    private S1Service s1Service;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(String data) {
        s1Mapper.insert(data);
        // 调用s2 想要当前类的insert2事务生效必须重新注入当前bean 调用 insert2方法
        s1Service.insert2(data);
    }

    //    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void insert2(String data) {
        s2Service.insert(data);

    }
}

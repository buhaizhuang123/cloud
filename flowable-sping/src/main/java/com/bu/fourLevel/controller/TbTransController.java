package com.bu.fourLevel.controller;

import com.bu.fourLevel.dao.TbTransMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2024/3/8 14:31
 * @mark TbTransController
 */
@RestController
@RequestMapping("/tbTrans")
public class TbTransController {

    @Autowired
    private TbTransMapper tbTransMapper;

    @Autowired
    private PlatformTransactionManager manager;

    @Autowired
    private TransactionDefinition transactionDefinition;



    @RequestMapping("/commitTransA")
    public String commitTransA() {
        TransactionStatus transaction = manager.getTransaction(transactionDefinition);
        tbTransMapper.insertTransInfo("A");
        transaction.isCompleted();
        manager.commit(transaction);
        int i = 1 / 0;

        tbTransMapper.insertTransInfo("B");
        return "事务A 提交成功、事务B 回滚成功";
    }


}

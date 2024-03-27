package com;

import com.cloud.ProductApp;
import com.cloud.person.service.S1Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author haizhuangbu
 * @date 2024/3/27 09:52
 * @mark TransactionTs
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApp.class)
public class TransactionTs {

    @Autowired
    private S1Service s1Service;

    @Test
    public void test() {
        s1Service.insert("测试数据");
    }

}

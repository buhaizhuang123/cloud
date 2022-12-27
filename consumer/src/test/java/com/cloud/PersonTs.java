package com.cloud;

import com.cloud.batch.dao.BusInfoMapper;
import com.cloud.batch.dto.BusInfo;
import com.cloud.common.ValidUtils;
import com.cloud.person.dto.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author haizhuangbu
 * @date 2022/7/31 15:26
 * @mark com.cloud.PersonTs
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@RefreshScope
public class PersonTs {
    @Autowired
    private BusInfoMapper busInfoMapper;

    @Test
    public void tsPersonVaild() {
        BusInfo info = new BusInfo();
        info.setBusClass("1111");
        info.setBusSts("1111");
        try {
            Integer integer = busInfoMapper.insertOne(info);
            System.out.println("integer = " + integer);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            System.out.println("e.getCause() = " + e.getCause());
            System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
        }
    }

    @Value("${ts1}")
    private String ts1;
    @Test
    public void t1(){
        System.out.println("ts1 = " + ts1);
    }


}

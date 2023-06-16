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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void t1() {
        System.out.println("ts1 = " + ts1);
    }


    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        List<Integer> list
                = Collections.synchronizedList(list1);
        for (int i = 0; i < 50; i++) {
            list.add(i );
        }
        PersonTs personTs = new PersonTs();
        list.parallelStream()
                .map(personTs::createList)
                .map(personTs::doList)
                .forEach(System.out::println);


    }

    public List createList(Integer s) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            list.add(i);
        }
        return list;
    }

    public String doList(List list){
        list.forEach(System.out::println);
        return "s";
    }



}

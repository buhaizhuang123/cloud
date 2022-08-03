package com.cloud;

import com.cloud.common.ValidUtils;
import com.cloud.person.dto.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author haizhuangbu
 * @date 2022/7/31 15:26
 * @mark com.cloud.PersonTs
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonTs {

    @Test
    public void tsPersonVaild() {
        Person person = new Person();
        String validBean = ValidUtils.validBean(person, Person.class);
        System.out.println(validBean);
    }

}

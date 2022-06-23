package com.cloud.func.service.impl;

import com.cloud.func.service.FuncInterface;
import com.cloud.func.vo.RuleResult;
import com.cloud.person.dto.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author haizhuangbu
 * @date 2022/6/18 21:26
 * @mark FuncEvent
 */
public class FuncEvent {

    public List<RuleResult> results = new ArrayList<>();

    public Map<String, Function<Person, Person>> map = new HashMap<>();

    {
        init();
    }

    public void init() {
        map.put("RULE1", (person) -> checkPerson(person));
    }

    public Function<Person, Person> check = (person -> {
        person.setName("李佳军");
        return person;
    });


    public Person checkPerson(Person person) {
        // 执行操作
        return check.apply(person);
    }

    public Person exe(Person person) {

        Function<Person, Person> personPersonFunction = map.get("RULE1");
        return personPersonFunction.apply(person);
    }


    public static void main(String[] args) {
        FuncEvent funcEvent = new FuncEvent();
        Person person = new Person();
        person.setName("buhaizhuang");
        System.out.println("funcEvent.exe(person) = " + funcEvent.exe(person));
    }

}

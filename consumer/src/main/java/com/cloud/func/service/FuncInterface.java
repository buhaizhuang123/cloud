package com.cloud.func.service;

import com.cloud.person.dto.Person;

import java.util.function.Function;

/**
 * @author haizhuangbu
 * @date 2022/6/18 21:00
 * @mark FuncInterface
 */
@FunctionalInterface
public interface FuncInterface {

    /**
     * @param person 参数
     */
    String apply(Person person);

}

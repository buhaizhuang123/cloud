package com.util;

import com.sun.istack.internal.NotNull;
import com.util.anno.Max;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:24
 * @mark Person
 */
public class Person {

    private String name;

    @Max(VALUE = 22 , MESSAGE = "年纪过大")
    private BigDecimal age;

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.util.check;

import com.util.Param;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:29
 * @mark Max
 */
public class Min implements Parent {

    @Override
    public String check(Annotation annotation, Param value) {
        com.util.anno.Min min = (com.util.anno.Min) annotation;
        BigDecimal val = value.getValue();
        return val.compareTo(BigDecimal.valueOf(min.VALUE())) < 0 ? min.MESSAGE() : "";
    }
}

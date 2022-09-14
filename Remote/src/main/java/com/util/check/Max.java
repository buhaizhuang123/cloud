package com.util.check;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:29
 * @mark Max
 */
public class Max implements Parent {

    @Override
    public String check(Annotation annotation, Object value) {
        com.util.anno.Max max = (com.util.anno.Max)annotation;
        BigDecimal val = (BigDecimal) value;
        return val.compareTo(BigDecimal.valueOf(max.VALUE())) > 0 ? max.MESSAGE() : "";
    }
}

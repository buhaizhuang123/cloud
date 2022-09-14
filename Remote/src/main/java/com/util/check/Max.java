package com.util.check;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:29
 * @mark Max
 */
public class Max implements Parent {

    @Override
    public String check(com.util.anno.Max max, BigDecimal value) {
        return value.compareTo(BigDecimal.valueOf(max.VALUE())) > 0 ? max.MESSAGE() : "";
    }
}

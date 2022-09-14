package com.util.check;

import com.util.anno.Max;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:35
 * @mark Parent
 */
public interface Parent {

    String check(Max max, BigDecimal value);

}

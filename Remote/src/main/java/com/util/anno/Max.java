package com.util.anno;


import com.util.em.LoanTypeEnum;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Max {
    double VALUE();

    String MESSAGE() default "";

    LoanTypeEnum get();
}

package com.util.anno;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Max {
    double VALUE();

    String MESSAGE() default "";
}

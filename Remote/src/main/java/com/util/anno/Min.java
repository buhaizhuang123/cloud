package com.util.anno;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Min {
    double VALUE();

    String MESSAGE() default "";
}

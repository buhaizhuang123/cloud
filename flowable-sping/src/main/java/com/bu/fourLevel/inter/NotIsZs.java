package com.bu.fourLevel.inter;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author haizhuangbu
 * @date 2024/2/1 16:51
 * @mark NotIsZs
 */
// 自定义校验注解必须添加的注解
@Constraint(
        validatedBy = {NotIsZs.NotIsZsImpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotIsZs {

    String message() default "{com.bu.fourLevel.inter.NotIsZs}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class NotIsZsImpl implements ConstraintValidator<com.bu.fourLevel.inter.NotIsZs, String> {

        // 获取校验注解中的内容
        @Override
        public void initialize(NotIsZs constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }


        // 校验规则
        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            if (StringUtils.isBlank(s)) return Boolean.TRUE;
            if (s.equals("ZS")) return false;
            return false;
        }
    }


}

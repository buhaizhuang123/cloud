package com.cloud.util;

import com.cloud.person.dto.Person;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/9/11 22:56
 * @mark BeanValidUtils
 */
public class BeanValidUtils<T> {

    private T data;


    public List<String> valid(T obj, Class<?> data) {
        // 默认解析类
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validate = validator.validate(obj, data);
        return validate.stream().map(va -> va.getMessage()).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        Person person = new Person();
        BeanValidUtils<Object> beanValidUtils = new BeanValidUtils<>();
        List<String> valid = beanValidUtils.valid(person, Person.class);
        System.out.println(valid);

    }


}

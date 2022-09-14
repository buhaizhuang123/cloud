package com.util;

import com.util.check.Max;
import com.util.check.Parent;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2022/9/14 14:22
 * @mark ValidUtils
 */
public class ValidUtils {

    private Map<String, Parent> checkMap = null;

    {
        checkMap = new HashMap();
        checkMap.put("Max", new Max());

    }


    public void valid(Object data) {

        Class<?> aClass = data.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object o = field.get(data);
                com.util.anno.Max[] annotationsByType = field.getAnnotationsByType(com.util.anno.Max.class);
                System.out.println(o);
                for (com.util.anno.Max max : annotationsByType) {
                    Parent max1 = checkMap.get(max.annotationType().getSimpleName());
                    String message = max1.check(max, (BigDecimal) o);
                    System.out.println("check = " + message);
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }


    }

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("123");
        person.setAge(BigDecimal.valueOf(79));
        ValidUtils validUtils = new ValidUtils();

        validUtils.valid(person);


    }


}

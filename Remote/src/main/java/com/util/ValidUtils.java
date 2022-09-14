package com.util;

import com.util.check.Max;
import com.util.check.Min;
import com.util.check.Parent;

import java.lang.annotation.Annotation;
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

    private static Map<String, Parent> checkMap = null;

    static {
        checkMap = new HashMap();
        checkMap.put("Max", new Max());
        checkMap.put("Min", new Min());

    }


    public static String valid(Object data) {

        Class<?> aClass = data.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object o = field.get(data);
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    String name = annotation.annotationType().getSimpleName();
                    Parent max1 = checkMap.get(name);
                    String message = max1.check(annotation, o);
                    return field.getName() + " : " + message;
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("123");
        person.setAge(BigDecimal.valueOf(1));

        System.out.println(ValidUtils.valid(person));


    }


}

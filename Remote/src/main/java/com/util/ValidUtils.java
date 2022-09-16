package com.util;

import com.util.check.Max;
import com.util.check.Min;
import com.util.check.Parent;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
                Object value = field.get(data);
                // 解析属性值
                Param param = parse(value);
                System.out.println(param);

                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    String name = annotation.annotationType().getSimpleName();
                    Parent max1 = checkMap.get(name);
                    String message = max1.check(annotation, param);
                    return field.getName() + " : " + message;
                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    private static Param parse(Object o) {
        // 参数
        Param param = new Param();
        if (o instanceof Number) {
            if (o instanceof BigDecimal) {
                BigDecimal bigDecimal = (BigDecimal) o;
                // 小数位
                int scale = bigDecimal.scale();
                param.setTail(scale);
                param.setValue(bigDecimal);
                param.setLength(bigDecimal.toString().length());
            } else {
                Double value = (Double) o;
                BigDecimal bigDecimal = BigDecimal.valueOf(value);
                // 小数位
                int scale = bigDecimal.scale();
                param.setTail(scale);
                param.setValue(bigDecimal);
                param.setLength(bigDecimal.toString().length());
            }

        } else {
            String value = (String) o;
            param.setLength(value.length());
        }


        return param;
    }

    public static void main(String[] args) {
//
//        Person person = new Person();
//        person.setName("123");
//        person.setAge(BigDecimal.valueOf(1));
//
//        System.out.println(ValidUtils.valid(person));

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/app.properties"));
            String name = properties.getProperty("name");
            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

package com.util;

import com.util.check.Max;
import com.util.check.Min;
import com.util.check.Parent;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /**
     * @param data   校验数据
     * @param cyclic 异常后是否校验后续字段
     * @param joint  是否拼接属性名 name:name字段不能为空
     */
    public static String valid(Object data, boolean cyclic, boolean joint) {

        Class<?> aClass = data.getClass();
        StringBuffer stf = new StringBuffer();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(data);
                // 解析属性值
                Param param = parse(value);
                // 获取注解信息
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    String name = annotation.annotationType().getSimpleName();
                    Parent parent = checkMap.get(name);
                    String message = parent.check(annotation, param);
                    String errorMsg = message;
                    // 是否拼接fieldName
                    if (joint && StringUtils.isNotBlank(message)) {
                        errorMsg = field.getName() + " : " + message + " ";
                    }
                    // 是否循环校验
                    if (cyclic && StringUtils.isNotBlank(message)) {
                        stf.append(errorMsg);
                    }
                    // 非循环校验 第一次异常直接抛出
                    if (!cyclic && StringUtils.isNotBlank(message)) {
                        return errorMsg;
                    }
                }
                return stf.toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * 解析参数 包含 长度 大小
     */
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
//        docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
//        -e "discovery.type=single-node" \
//        -e ES_JAVA_OPTS="-Xms64m -Xmx512m" \
//        -v /Applications/tools/myConfig/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
//        -v /Applications/tools/myConfig/data:/usr/share/elasticsearch/data \
//        -v  /Applications/tools/myConfig/plugins:/usr/share/elasticsearch/plugins \
//        -d elasticsearch:7.17.3
//
//
//        docker run --name kibana -e ELASTICSEARCH_HOSTS=http://192.168.0.113:9200 -p 5601:5601 -v /Applications/tools/myConfig/config/kibana.yml:/usr/share/kibana/config/kibana.yml -d kibana:7.17.3
//
//        docker run -d --restart=always --log-driver json-file --log-opt max-size=100m --log-opt max-file=2 -p 5044:5044 --name logstash -v /Applications/tools/myConfig/logstash/logstash.yml:/usr/share/logstash/config/logstash.yml -v /Applications/tools/myConfig/logstash/conf.d/:/data/docker/logstash/conf.d/ logstash:7.17.3
//

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

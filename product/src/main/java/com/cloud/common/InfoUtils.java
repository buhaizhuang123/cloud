package com.cloud.common;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/5/4 11:35
 * @mark InfoUtils
 */
public class InfoUtils {



    private static   Properties properties;


   static  {
        properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
    }

    /**
     * @param jsonInfo 消息
     * @param topic 主题
     */
    public static void send(String topic,String jsonInfo){
        KafkaProducer producer = new KafkaProducer(properties);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,jsonInfo);
        producer.send(record);
        // 关闭
        producer.close();
    }


}

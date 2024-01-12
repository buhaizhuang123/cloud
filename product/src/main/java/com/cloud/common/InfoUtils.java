package com.cloud.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/5/4 11:35
 * @mark InfoUtils
 */
public class InfoUtils {


    private static Properties properties;


    static {
        properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "demo");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
    }

    /**
     * @param jsonInfo 消息
     * @param topic    主题
     */
    public static void send(String topic, String jsonInfo) {
        KafkaProducer producer = new KafkaProducer(properties);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, jsonInfo);
        producer.send(record);
        // 关闭
        producer.close();
    }

    /**
     * @param topic 主题
     * @mark 拉去主题信息
     */
    public static String pull(String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> datas = consumer.poll(Duration.of(1000, ChronoUnit.MILLIS));
        StringBuffer stringBuffer = new StringBuffer();
        // 消费数据
        for (ConsumerRecord<String, String> data : datas) {
            stringBuffer.append(data.value());
        }
        consumer.close();
        return stringBuffer.toString();
    }


}

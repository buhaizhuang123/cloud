package com.cloud.common;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/5/4 11:42
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


    private static Logger logger = LoggerFactory.getLogger(InfoUtils.class);

    /**
     * @param topic 主题
     */
    public static String pull(String topic, Properties properties) {
        KafkaConsumer<String, String> consumer = properties != null ? new KafkaConsumer<>(properties) : new KafkaConsumer<>(InfoUtils.properties);
        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        StringBuffer stringBuffer = new StringBuffer();
        Iterable<ConsumerRecord<String, String>> records1 = records.records(topic);
        for (ConsumerRecord<String, String> stringStringConsumerRecord : records1) {
            stringBuffer.append(stringStringConsumerRecord.value());
        }
        consumer.close();
        return stringBuffer.toString();
    }

    public static String pullOther(String topic, String groupId) {
        Properties pro = (Properties) properties.clone();
        pro.setProperty("group.id", groupId);
        return pull(topic, pro);
    }

    public static String pull(String topic) {
        return pull(topic, null);
    }


    public static void send(String topic, Object info) {
        KafkaProducer<String, Object> producer = new KafkaProducer<>(properties);
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, info);
        // 发送并忘记
        producer.send(record, (data, err) -> {
            if (err != null) {
                System.out.println("错误信息" + err);
            }

            if (data != null) {
                logger.info("{}", data);
            }
        });

        // 异步回调
//        producer.send(record,(data,err) -> {
//
//        });

        // 同步返回
//        RecordMetadata recordMetadata = producer.send(record).get();
        logger.info("发送数据");
    }

}

package com.cloud.common.kfk;

import com.cloud.sink.MysqlSink;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author haizhuangbu
 * @date 2022/6/11 13:50
 * @mark WorldCountKafka
 */
public class WorldCountKafka {


    public static void main(String[] args) throws Exception {


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);
        env.setRestartStrategy(RestartStrategies.failureRateRestart(3, Time.of(5, TimeUnit.MINUTES), Time.of(10, TimeUnit.SECONDS)));
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.enableCheckpointing(5000);
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "flink");

//        properties.setProperty("auto.offset.reset", "latest");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer("flink", new SimpleStringSchema(), properties);

        DataStreamSource dataStreamSource = env.addSource(consumer);

        SingleOutputStreamOperator sum = dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String data, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] datas = data.split(" ");
                Arrays.stream(datas).map(str -> {
                    System.out.println(str);
                    collector.collect(new Tuple2<>(str, 1));
                    return str;
                }).count();
            }
        }).keyBy(0).sum(1);

        sum.addSink(new MysqlSink());
        System.out.println("===============================");
        env.execute("kafka consumer");

    }

}

package com.cloud.common;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @author haizhuangbu
 * @date 2022/6/11 13:37
 * @mark WordCount
 */
public class WordCount {


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> source = env.socketTextStream("localhost", 8811);


        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = source.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {


            @Override
            public void flatMap(String data, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] datas = data.split(" ");
                Arrays.stream(datas).map(str -> {
                    collector.collect(new Tuple2<>(str, 1));
                    return str;
                }).count();
            }
        }).keyBy(0).sum(1);


        sum.print().setParallelism(1);


        env.execute("word count!!!");


    }

}

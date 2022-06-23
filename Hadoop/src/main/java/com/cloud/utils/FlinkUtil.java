package com.cloud.utils;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/6/11 10:29
 * @mark FlinkUtil
 */
public class FlinkUtil {

    /**
     * 批处理
     */
    private ExecutionEnvironment executionEnvironment;


    public static void main(String[] args) throws Exception {
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> dataSource = executionEnvironment.fromElements("JAVA JAVA PYTHON", "C## C++");
        AggregateOperator<Tuple2<String, Integer>> sum = dataSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {

            @Override
            public void flatMap(String data, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] datas = data.split(" ");
                Arrays.stream(datas)
                        .map(i -> {
                            collector.collect(new Tuple2<String, Integer>(i, 1));
                            return i;
                        }).count();
            }
        }).groupBy(0).sum(1);
        sum.print();
//        executionEnvironment.execute("job 1");
    }

}

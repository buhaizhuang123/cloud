package com.cloud.hp.service.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/9/3 22:38
 * @mark WorldMapper
 */
public class WorldMapper implements Mapper<LongWritable, Text, Text, IntWritable> {

    private final IntWritable DEFAULT_VALUE = new IntWritable(1);

    @Override
    public void map(LongWritable text, Text text2, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        String data = text2.toString();
        String[] arr = data.split(" ");
        for (String s : arr) {
            Text key = new Text(s);
            outputCollector.collect(key, DEFAULT_VALUE);
        }
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}

package com.cloud.hp.service.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author haizhuangbu
 * @date 2022/9/4 08:33
 * @mark WorldReducer
 */
public class WorldReducer implements Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text text, Iterator<IntWritable> iterator, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
        int total = 0;
        while (iterator.hasNext()) {
            IntWritable next = iterator.next();
            total += next.get();
        }
        outputCollector.collect(text, new IntWritable(total));
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void configure(JobConf jobConf) {

    }
}

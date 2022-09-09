package com.cloud.hp.service.impl;

import com.cloud.hp.service.mapper.WorldMapper;
import com.cloud.hp.service.reducer.WorldReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/9/3 22:22
 * @mark JobWorker
 */
public class JobWorker {

    public static void main(String[] args) throws IOException {
        System.setProperty("HADOOP_USER_NAME", "root");
        String inputStr = "hdfs://hadoop01:9000/123.txt";
        String outputStr = "hdfs://hadoop01:9000/emp/weater";
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://hadoop01:9000");
        configuration.set("dfs.client.use.datanode.hostname", "true");
        JobConf jobConf = new JobConf(configuration);
        // 任务名称
        jobConf.setJobName("温度检测");

        // 设置输入方法
        jobConf.setMapperClass(WorldMapper.class);

        jobConf.setMapOutputKeyClass(Text.class);
        jobConf.setMapOutputValueClass(IntWritable.class);
        // 设置输出key类型
        jobConf.setOutputKeyClass(Text.class);
        // 设置输出value类型
        jobConf.setOutputValueClass(IntWritable.class);

        // 设置输出方法
        jobConf.setReducerClass(WorldReducer.class);
//        // 设置中间方法
//        jobConf.setCombinerClass(WorldReducer.class);

        jobConf.setInputFormat(TextInputFormat.class);

        jobConf.setOutputFormat(TextOutputFormat.class);

        // 设置输入数据文件路径
        FileInputFormat.setInputPaths(jobConf, new Path(inputStr));
        // 设置输出文件路径
        FileOutputFormat.setOutputPath(jobConf, new Path(outputStr));

        // 启动任务
        JobClient.runJob(jobConf);
        System.exit(0);
    }

}

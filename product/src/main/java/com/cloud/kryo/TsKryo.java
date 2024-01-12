package com.cloud.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author haizhuangbu
 * @date 2022/8/10 10:14
 * @mark TsKryo
 */
public class TsKryo {


    @Test
    public void ser() {
        // 序列化
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        String name = "kryo11111";
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("kryo.result");
            Output output = new Output(outputStream);
            kryo.writeClassAndObject(output, name);
            System.out.println("数据发送完成");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                outputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Test
    public void read(){
        Kryo kryo = new Kryo();
        // 反序列化
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("kryo.result");
            Input input = new Input(fileInputStream);
            String t =  kryo.readObject(input,String.class);
            System.out.println("反序列化之后的值:" + t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

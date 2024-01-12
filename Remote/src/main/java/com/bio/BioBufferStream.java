package com.bio;

import java.io.*;
import java.nio.CharBuffer;

/**
 * @author haizhuangbu
 * @date 2022/7/25 14:22
 * @mark BioBufferStream
 */
public class BioBufferStream {

    public static void main(String[] args) {
        InputStreamReader reader  = null;
        try {
            String pack = BioBufferStream.class.getResource("/").getPath();
            reader  = new InputStreamReader(new FileInputStream(pack + "01.txt"));
            CharBuffer buff = CharBuffer.allocate(1024);
            while (reader.read(buff) > 0) {
                System.out.println("读取数据");
                buff.flip(); // 切换校验
                String s = buff.toString();
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}

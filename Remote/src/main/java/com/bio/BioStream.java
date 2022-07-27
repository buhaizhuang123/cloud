package com.bio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 2022/7/25 14:18
 * @mark BioStream BIO读取数据
 */
public class BioStream {


    public static void main(String[] args) {
        String path = BioStream.class.getResource("/").getPath();
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(new File(path + "01.txt"));
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fs.read(bytes)) > 0) {
                System.out.println(new String(bytes,0,len));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}

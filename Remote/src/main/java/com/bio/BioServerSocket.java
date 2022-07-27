package com.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2022/7/25 14:31
 * @mark BioServerSocket 单次通信
 */
public class BioServerSocket {


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        InputStream ins =null;
        OutputStream ous = null;
        try {
            serverSocket = new ServerSocket(8991);
            Socket socket = serverSocket.accept();
            // 输入流
            ins = socket.getInputStream();
            // 输出流
            ous = socket.getOutputStream();
            byte [] buf = new byte[1024];
            int len = 0 ;
            if ((len = ins.read(buf)) > 0) {
                System.out.println(new String(buf,0,len));
                doWrite(ous,"数据");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭");
            try {
                assert ins != null;
                ins.close();
                assert ous != null;
                ous.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    private static void doWrite(OutputStream ous, String xinxi) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(xinxi);
        try {
            ous.write(buffer.toString().getBytes("GB2312"));
            ous.flush();
            ous.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

package com.lerNio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author haizhuangbu
 * @date 2023/2/28 09:57
 * @mark SendNio
 */
public class SendNio {

    public static void send() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8099);
        SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);

        socketChannel.configureBlocking(false);
        while (! socketChannel.finishConnect());
        System.out.println("======= 客户端连接成功 =======");
        ByteBuffer buff = ByteBuffer.allocate(1024);
        buff.put("Hello World".getBytes("utf-8"));
        buff.flip();
        socketChannel.write(buff);
        while (true) {
            buff.clear();
            int len;
            while ((len = socketChannel.read(buff)) > 0) {
                buff.flip();
                System.out.println("======== 读取信息 ========");
                System.out.println(new String(buff.array(),0,len));

                buff.clear();
            }

            break;
        }

        socketChannel.shutdownOutput();
        socketChannel.close();
    }


    public static void main(String[] args) throws IOException {
        send();
    }


}

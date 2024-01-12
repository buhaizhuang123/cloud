package com.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 2022/7/25 16:50
 * @mark C01
 */
public class C01 {

    public static void main(String[] args) {

        try {


            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8111));

            while (!socketChannel.finishConnect()) {

            }
            System.out.println("发送消息");
            ByteBuffer wrap = ByteBuffer.wrap("我是最新的消息".getBytes(StandardCharsets.UTF_8));

            socketChannel.write(wrap);
            System.out.println("读取消息");

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer) == -1) {
                socketChannel.close();
            }

            buffer.flip();
            byte[] array = buffer.array();
            System.out.println(new String(array, 0, buffer.limit()));
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

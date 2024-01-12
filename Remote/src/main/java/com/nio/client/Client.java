package com.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2022/7/25 15:34
 * @mark Client
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8111));
            while (!socketChannel.isConnected()) {
                socketChannel.finishConnect();
            }

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("信息".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            assert socketChannel != null;
            socketChannel.close();
        }


    }


}

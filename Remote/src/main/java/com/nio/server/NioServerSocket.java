package com.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2022/7/25 15:00
 * @mark NioServerSocket
 */
public class NioServerSocket {


    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8111));
            serverSocketChannel.configureBlocking(false);
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel == null) socketChannel = serverSocketChannel.accept();
            socketChannel.read(buffer);
            buffer.flip();
            byte[] array = buffer.array();
            System.out.println("--------  "+new String(array,0,buffer.limit()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert serverSocketChannel != null;
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


}

package com.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 2022/7/25 16:50
 * @mark R01
 */
public class R01 {

    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;

        try {
            Selector selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8111));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(1000) == 0) {
                    System.out.println("无服务连接");
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    System.out.println("进来了");
                    if (selectionKey.isAcceptable()) {
                        System.out.println("建立连接");
                        ServerSocketChannel sc = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = sc.accept();
                        if (socketChannel == null) continue;
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("建立连接完毕");
                    }

                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        System.out.println("读取文件信息");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        if (socketChannel.read(buffer) == -1) {
                            socketChannel.close();
                        }
                        buffer.flip();
                        buffer.clear();
                        byte[] array = buffer.array();
                        System.out.println("========" + new String(array, 0, buffer.limit()));
                        StringBuilder sb = new StringBuilder();
                        sb.append("HTTP/1.1 200 OK\n")
                                .append("Content-Type: text/html;\n")
                                .append("\r\n")
                                .append(new String(array, 0, buffer.limit()));
                        buffer = ByteBuffer.wrap(sb.toString().getBytes(StandardCharsets.UTF_8));
                        socketChannel.write(buffer);
                        socketChannel.close();
                        System.out.println("读取文件信息完毕");

                    }
                    iterator.remove();


//                    if (selectionKey.isWritable()){
//                        System.out.println("输出文件信息");
//                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
//                        StringBuilder sb = new StringBuilder();
//                        sb.append("HTTP/1.1 200 OK\n")
//                                .append("Content-Type: text/html;\n")
//                                .append("\r\n")
//                                .append("info");
//                        ByteBuffer wrap = ByteBuffer.wrap(sb.toString().getBytes(StandardCharsets.UTF_8));
//                        socketChannel.write(wrap);
//                        socketChannel.register(selector,selectionKey.interestOps() -  SelectionKey.OP_WRITE);
//                        socketChannel.close();
//                        System.out.println("输出文件信息完毕");
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

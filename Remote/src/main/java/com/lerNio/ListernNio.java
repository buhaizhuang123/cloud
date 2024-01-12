package com.lerNio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 2023/2/28 09:38
 * @mark ListernNio 监听
 */
public class ListernNio {


    public static void listen() throws IOException {

        Selector selector = Selector.open();
        // 开通管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        // 设置监听端口
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8099));
        // 监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {

                SelectionKey sel = iterator.next();
                if (sel.isAcceptable()) {
                    // 保持监听
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("======== 连接成功 ========");
                    // 切换为读事件
                    socketChannel.register(selector,SelectionKey.OP_READ);
                } else if (sel.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) sel.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len  = 0;

                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println("======== 读取信息 ========");
                        System.out.println(new String(buffer.array(),0,len));

                        buffer.clear();
                    }
                    socketChannel.close();
                }
                iterator.remove();
            }
        }
        serverSocketChannel.close();
    }


    public static void main(String[] args) throws IOException {

        listen();

    }

}

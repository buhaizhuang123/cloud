package com.cloud.chain.netty.main;

import com.cloud.chain.netty.dto.GRequest;
import com.cloud.chain.netty.dto.GResponse;
import com.cloud.chain.netty.service.BzTomcatHandler;
import com.cloud.chain.netty.service.TmServlet;
import com.cloud.chain.tomcat.server.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/7/23 08:19
 * @mark Tomcat
 */
public class Tomcat {


    private Properties webXml = new Properties();

    public Map<String, TmServlet> mappingMap = new HashMap<>();

    private Integer port = 8888;

    /**
     * init
     */
    void init() {
        FileInputStream fileInputStream = null;
        try {
            String path = this.getClass().getResource("/").getPath();
            fileInputStream = new FileInputStream(path + "web1.properties");
            webXml.load(fileInputStream);
            mappingMap = webXml.keySet().stream()
                    .map(Object::toString)
                    .filter(i -> i.endsWith(".url"))
                    .collect(Collectors.toMap(a -> webXml.getProperty(a), a -> {
                        String head = a.replaceAll("\\.url$", "");
                        try {
                            return (TmServlet) Class.forName(webXml.getProperty(head + ".className")).newInstance();
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void start() {
        // 初始化
        init();
        // boss线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 创建对象
            ServerBootstrap server = new ServerBootstrap();
            // 2.配置参数
            // 链路编程
            server.group(bossGroup, workerGroup)
                    // 主线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel client) throws Exception {
                            client.pipeline()
                                    .addLast(new HttpRequestDecoder());
                            client.pipeline().addLast(new HttpResponseEncoder());
                            client.pipeline().addLast(new BzTomcatHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            ChannelFuture funture = server.bind(port).sync();
            System.out.println("服务器已经启动,监听端口是:" + port);
            funture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        new Tomcat().start();
    }

    public class BzTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            // 读取数据
            if (msg instanceof HttpRequest) {

                System.out.println("in to .....");
                HttpRequest req = (HttpRequest) msg;

                GRequest request = new GRequest(ctx, req);
                GResponse response = new GResponse(ctx, req);
                // 实际业务逻辑处理
                String url = request.getUrl();
                if (mappingMap.containsKey(url)) {
                    mappingMap.get(url).service(request, response);
                } else {
                    response.writer("404 - not Found");
                }
            }


        }
    }

}

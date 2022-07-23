package com.cloud.rpc.provider.handler;

import com.cloud.rpc.provider.dto.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haizhuangbu
 * @date 2022/7/23 12:33
 * @mark MorChannelHandler
 */
public class RegisterHandler extends ChannelInboundHandlerAdapter {


    // 保存所有可用服务
    public static ConcurrentHashMap<String, Object> registerMap = new ConcurrentHashMap<>();

    // 保存相关的服务类
    private List<String> classNames = new ArrayList<>();

    public RegisterHandler() {

        // 递归扫描
        scannerClassName("com.cloud.rpc.provider");
        // 注册
        doRegister();

    }

    private void doRegister() {

        if (classNames.size() == 0) return;

        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if (clazz.getInterfaces().length > 0) {
                    Class<?> anInterface = clazz.getInterfaces()[0];
                    registerMap.put(anInterface.getName(), clazz.newInstance());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void scannerClassName(String path) {

        URL url = this.getClass().getClassLoader().getResource(path.replaceAll("\\.", "/"));

        File dirs = new File(url.getFile());

        for (File file : dirs.listFiles()) {

            if (file.isDirectory()) {
                scannerClassName(path + "." + file.getName());
            } else {
                classNames.add(path + "." + file.getName().replaceAll(".class", ""));
            }
        }
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object req) throws Exception {
        InvokerProtocol rpc = (InvokerProtocol) req;
        Object result = new Object();
        if (registerMap.containsKey(rpc.getClassName())) {
            Object clazz = registerMap.get(rpc.getClassName());
            Method method = clazz.getClass().getMethod(rpc.getMethodName(), rpc.getParames());
            result = method.invoke(clazz, rpc.getValues());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();

    }
}

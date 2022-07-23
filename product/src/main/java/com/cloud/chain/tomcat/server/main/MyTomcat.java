package com.cloud.chain.tomcat.server.main;

import com.cloud.chain.tomcat.dto.BzRequest;
import com.cloud.chain.tomcat.dto.BzResponse;
import com.cloud.chain.tomcat.server.MyServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:43
 * @mark MyTomcat
 */
public class MyTomcat {

    private Properties properties = new Properties();

    private Map<String, MyServlet> map = new HashMap<>();

    private Integer port = 8888;

    public void init() {
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fileInputStream = new FileInputStream(path + "web.properties");
            properties.load(fileInputStream);
            for (Object o : properties.keySet()) {
                String info = o.toString();
                if (info.endsWith(".url")) {
                    String servletName = info.replaceAll("\\.url$", "");
                    String url = properties.getProperty(info);
                    String className = properties.getProperty(servletName + ".className");
                    MyServlet servlet = (MyServlet) Class.forName(className).newInstance();
                    map.put(url, servlet);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        // 加载配置文件
        init();
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("端口已启动:" + this.port);
            while (true) {
                Socket client = serverSocket.accept();
                proccess(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void proccess(Socket client) throws IOException {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();
        BzRequest request = new BzRequest(inputStream);
        BzResponse bzResponse = new BzResponse(outputStream);
        String url = request.getUrl();
        if (map.containsKey(url)) {
            map.get(url).doGet(request, bzResponse);
        } else {
            bzResponse.writer("404 Not Found!!!");
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        client.close();
    }


}

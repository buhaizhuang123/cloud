package com.cloud.chain.tomcat.dto;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:25
 * @mark BzResponse
 */
public class BzResponse {

    private OutputStream outputStream;

    public BzResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public void writer(String info) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(info);
        try {
            outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

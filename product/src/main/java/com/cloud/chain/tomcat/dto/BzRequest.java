package com.cloud.chain.tomcat.dto;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author haizhuangbu
 * @date 2022/7/22 22:24
 * @mark BzRequest
 */
@Data
public class BzRequest {

    private String method;

    private String url;

    /**
     * 解析
     */
    public BzRequest(InputStream inputStream) {
        try {
            byte[] bytes = new byte[1024];
            int length = 0;
            String content = null;
            if ((length = inputStream.read(bytes)) > 0) {
                content = new String(bytes, 0, length);
            }
            String line = content.split("\n")[0];
            String[] arr = line.split("\\s");
            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

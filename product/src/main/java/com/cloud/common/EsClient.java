package com.cloud.common;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 2022/5/28 10:15
 * @mark EsClient
 */
public class EsClient {
    private static RestHighLevelClient restHighLevelClient;

    /**
     * 链接方法
     */
    public static RestHighLevelClient builder() {
        if (Objects.isNull(restHighLevelClient)) {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
        }
        return restHighLevelClient;
    }

    public static String colse() {
        try {
            restHighLevelClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "关闭成功";
    }
}

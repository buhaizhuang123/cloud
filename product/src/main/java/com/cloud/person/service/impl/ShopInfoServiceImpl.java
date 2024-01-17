package com.cloud.person.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.EsClient;
import com.cloud.common.vo.ResultVo;
import com.cloud.person.dto.ShopInfoDto;
import com.cloud.person.service.ShopInfoService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:08
 * @mark ShopServiceImpl
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    @Override
    public ResultVo<String> addShopInfo(ShopInfoDto shopInfoDto) {
        RestHighLevelClient restHighLevelClient = EsClient.builder();
        IndexRequest indexRequest = new IndexRequest("shop", "_doc");
        Map<String, Object> source = JSONObject.parseObject(JSONObject.toJSONString(shopInfoDto), Map.class);
        indexRequest.source(source);
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResultVo.success("插入成功");
    }
}

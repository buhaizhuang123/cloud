package com.cloud.person.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.EsClient;
import com.cloud.common.vo.ResultVo;
import com.cloud.person.dao.ShopInfoRepository;
import com.cloud.person.dto.ShopInfoDto;
import com.cloud.person.service.ShopInfoService;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:08
 * @mark ShopServiceImpl
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    private ShopInfoRepository shopInfoRepository;

    @Override
    public ResultVo<String> addShopInfo(ShopInfoDto shopInfoDto) {
//        RestHighLevelClient restHighLevelClient = EsClient.builder();
//        IndexRequest indexRequest = new IndexRequest("shop", "_doc");
//        Map<String, Object> source = JSONObject.parseObject(JSONObject.toJSONString(shopInfoDto), Map.class);
//        indexRequest.source(source);
//        try {
//            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        shopInfoRepository.index(shopInfoDto);
        return ResultVo.success("插入成功");
    }

    @Override
    public ResultVo<Page<ShopInfoDto>> listShop(Integer pageNum, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNum, pageSize);
        return ResultVo.success(shopInfoRepository.findAll(page));
    }
}

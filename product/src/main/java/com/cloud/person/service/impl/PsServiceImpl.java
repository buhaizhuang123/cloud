package com.cloud.person.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.common.EsClient;
import com.cloud.person.dto.PersonDto;
import com.cloud.person.service.PsService;
import com.cloud.shop.dto.Page;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/5/28 12:03
 * @mark PsServiceImpl
 */
@Service
public class PsServiceImpl implements PsService {


    @Override
    public Object listPs(Page page) throws IOException {

        RestHighLevelClient restHighLevelClient = EsClient.builder();
        SearchRequest searchRequest = new SearchRequest("p1");
        MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        searchSourceBuilder.from(page.getPageNum())
                .size(page.getSize())
                .query(matchAllQueryBuilder)
                .sort("birthday", SortOrder.DESC);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        SearchHit[] hits1 = hits.getHits();
        ArrayList<Object> objects = new ArrayList<>();
        for (SearchHit documentFields : hits1) {
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            objects.add(sourceAsMap);
        }
        return objects;
    }

    @Override
    public List<PersonDto> findPerson(PersonDto personDto, Page page) throws IOException {
        RestHighLevelClient restHighLevelClient = EsClient.builder();
        SearchRequest searchRequest = new SearchRequest("person", "person1");
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        if (Objects.isNull(personDto) || !StringUtils.hasText(personDto.getName())) {
            MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder).from(page.getPageNum()).size(page.getSize());
        } else {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", personDto.getName());
            searchSourceBuilder.query(matchQueryBuilder).from(page.getPageNum()).size(page.getSize());
        }
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits()
                .getHits();
        List<PersonDto> personDtos = Arrays.stream(hits)
                .map(SearchHit::getSourceAsString)
                .map(json -> JSONObject.parseObject(json, PersonDto.class))
                .collect(Collectors.toList());
        return personDtos;
    }

    @Override
    public DocWriteResponse.Result save(PersonDto personDto) throws IOException {
        personDto.setBirthday("2020-12-11");
        RestHighLevelClient restHighLevelClient = EsClient.builder();
        IndexRequest index = new IndexRequest("person", "_doc");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(personDto));
        index.source(jsonObject);
        IndexResponse response = restHighLevelClient.index(index, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = response.getResult();
        return result;
    }
}

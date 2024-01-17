package com.cloud.person.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.common.EsClient;
import com.cloud.person.dto.PersonDto;
import com.cloud.person.dto.PersonVo;
import com.cloud.person.service.PsService;
import com.cloud.shop.dto.Page;
import com.google.common.base.Supplier;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchAction;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
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
        EsClient.close();
        return objects;
    }

    @Override
    public List<PersonDto> findPerson(PersonVo personDto, Page page) throws IOException {
        RestHighLevelClient restHighLevelClient = EsClient.builder();
        SearchRequest searchRequest = new SearchRequest("person");
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource();
        if (Objects.isNull(personDto) && StringUtils.isBlank(personDto.getValue())) {
            MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder).from(page.getPageNum()).size(page.getSize());
        } else if (Objects.nonNull(personDto.getTypes()) && personDto.getTypes().length >= 1) {
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(personDto.getValue(), personDto.getTypes());
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            if (personDto.getTypes().length > 1) {
                List<HighlightBuilder.Field> fields = highlightBuilder.fields();
                List<HighlightBuilder.Field> list = Arrays.stream(personDto.getTypes()).map(HighlightBuilder.Field::new).collect(Collectors.toList());
                fields.addAll(list);
            } else {
                String type = personDto.getTypes()[0];
                highlightBuilder.field(type);
            }
            highlightBuilder.preTags("<span style='color:red'>").postTags("</span>");
            searchSourceBuilder.query(multiMatchQueryBuilder).from(page.getPageNum()).size(page.getSize())
                    .highlighter(highlightBuilder);
        } else if (StringUtils.isNotBlank(personDto.getType())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field(personDto.getType()).preTags("<span style='color:red'>").postTags("</span>");
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(personDto.getType(), personDto.getValue());
            searchSourceBuilder.query(matchQueryBuilder).highlighter(highlightBuilder).from(page.getPageNum()).size(page.getSize());
        }


        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        EsClient.close();
        return getResult(response, personDto);

    }

    private List<PersonDto> getResult(SearchResponse response, PersonVo personDto) {
        SearchHit[] hits = response.getHits()
                .getHits();
        if (Objects.nonNull(personDto.getTypes()) && personDto.getTypes().length >= 1) {
            ArrayList<PersonDto> personDtos = new ArrayList<>();
            for (SearchHit hit : hits) {

                // 获取资源
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                for (String type : personDto.getTypes()) {
                    HighlightField highlightField = highlightFields.get(type);
                    if (highlightField != null) {
                        String value = Arrays.stream(highlightField.getFragments()).map(Text::toString).collect(Collectors.joining());
                        sourceAsMap.put(type, value);
                    }
                }
                PersonDto result = JSONObject.parseObject(JSONObject.toJSONString(sourceAsMap), PersonDto.class);
                result.setId(hit.getId());
                personDtos.add(result);
            }
            return personDtos;
        } else {
            List<PersonDto> personDtos = Arrays.stream(hits)
                    .map(search -> {
                        String sourceAsString = search.getSourceAsString();
                        Map<String, HighlightField> highlightFields = search.getHighlightFields();
                        // 拼接颜色字符
                        String value = Optional.ofNullable(highlightFields)
                                .map(map -> map.get(personDto.getType()))
                                .map(HighlightField::getFragments)
                                .map(tx -> Arrays.stream(tx).map(Text::toString).collect(Collectors.joining()))
                                .orElse("");
                        Map map1 = JSONObject.parseObject(sourceAsString, Map.class);
                        if (StringUtils.isNotBlank(value)) {
                            HashMap<Object, Object> map = new HashMap<>();
                            map.put(personDto.getType(), value);
                            map1.putAll(map);
                        }
                        PersonDto personDto1 = JSONObject.parseObject(JSONObject.toJSONString(map1), PersonDto.class);
                        personDto1.setId(search.getId());
                        return personDto1;
                    })
                    .collect(Collectors.toList());
            return personDtos;
        }
    }

    @Override
    public DocWriteResponse.Result save(PersonDto personDto) throws IOException {
        RestHighLevelClient restHighLevelClient = EsClient.builder();
        IndexRequest index = new IndexRequest("person", "_doc");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(personDto));
        index.source(jsonObject);
        IndexResponse response = restHighLevelClient.index(index, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = response.getResult();
        EsClient.close();
        return result;
    }


    @Override
    @Transactional
    public DeleteResponse delToPerson(String id) {

        DeleteRequest deleteRequest = new DeleteRequest("person", "_doc", id);
        RestHighLevelClient builder = EsClient.builder();
        // 删除
        try {
            DeleteResponse delete = builder.delete(deleteRequest, RequestOptions.DEFAULT);
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
        }
        EsClient.close();
        return null;
    }

}

package com.cloud.person.dao;

import com.cloud.person.dto.ShopInfoDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author haizhuangbu
 * @date 2024/1/17 16:52
 * @mark ShopInfoRepository
 */
public interface ShopInfoRepository extends ElasticsearchRepository<ShopInfoDto, String> {
}

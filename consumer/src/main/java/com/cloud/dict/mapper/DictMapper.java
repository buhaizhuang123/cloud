package com.cloud.dict.mapper;

import com.cloud.dict.dto.Dict;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:16
 * @mark DictMapper
 */
public interface DictMapper {

    public List<Dict> searchAll();

}

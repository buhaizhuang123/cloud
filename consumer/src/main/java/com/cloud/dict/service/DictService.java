package com.cloud.dict.service;

import com.cloud.dict.dto.Dict;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:25
 * @mark DictService
 */
public interface DictService {

    List<Dict> query();

}

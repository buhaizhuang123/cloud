package com.cloud.dict.service.impl;

import com.cloud.dict.dto.Dict;
import com.cloud.dict.mapper.DictMapper;
import com.cloud.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:26
 * @mark DictServiceImpl
 */
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> query() {
        return dictMapper.searchAll();
    }
}

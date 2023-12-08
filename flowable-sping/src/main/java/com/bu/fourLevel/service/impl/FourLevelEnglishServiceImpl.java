package com.bu.fourLevel.service.impl;

import com.bu.fourLevel.dao.FourLevelEnglishMapper;
import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.bu.fourLevel.service.FourLevelEnglishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/30 12:47
 * @mark FourLevelEnglishServiceImpl
 */
@Service
public class FourLevelEnglishServiceImpl implements FourLevelEnglishService {

    @Autowired
    private FourLevelEnglishMapper fourLevelEnglishMapper;


    @Override
    public PageInfo<FourLevelEnglishDto> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FourLevelEnglishDto> list = fourLevelEnglishMapper.list();
        return new PageInfo<>(list);
    }


}

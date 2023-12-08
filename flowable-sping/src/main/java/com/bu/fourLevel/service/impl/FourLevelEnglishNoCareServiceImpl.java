package com.bu.fourLevel.service.impl;

import com.bu.fourLevel.dao.EnglishNoCareMapper;
import com.bu.fourLevel.dto.EnglishNoCareDto;
import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.bu.fourLevel.service.FourLevelEnglishNoCareService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/12/5 11:18
 * @mark FourLevelEnglishNoCareServiceImpl
 */
@Service
public class FourLevelEnglishNoCareServiceImpl implements FourLevelEnglishNoCareService {

    @Autowired
    private EnglishNoCareMapper englishNoCareMapper;

    @Override
    public Integer addEnglishNoCare(EnglishNoCareDto noCareDto) {
        return englishNoCareMapper.addEnglishNoCare(noCareDto);
    }

    @Override
    public PageInfo<FourLevelEnglishDto> listEnglish(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FourLevelEnglishDto> list = englishNoCareMapper.listEnglish();
        return new PageInfo<FourLevelEnglishDto>(list);
    }

    @Override
    public Integer deleteEnglishNoCare(EnglishNoCareDto noCareDto) {
        return englishNoCareMapper.deleteEnglishNoCare(noCareDto);
    }
}

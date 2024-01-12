package com.bu.fourLevel.service;

import com.bu.fourLevel.dto.EnglishNoCareDto;
import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.github.pagehelper.PageInfo;

/**
 * @author haizhuangbu
 * @date 2023/12/5 11:17
 * @mark FourLevelEnglishNoCareService
 */
public interface FourLevelEnglishNoCareService {

    Integer addEnglishNoCare(EnglishNoCareDto noCareDto);


    PageInfo<FourLevelEnglishDto> listEnglish(Integer pageNum, Integer pageSize);

    Integer deleteEnglishNoCare(EnglishNoCareDto noCareDto);
}

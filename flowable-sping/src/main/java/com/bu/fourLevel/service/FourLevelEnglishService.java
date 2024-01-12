package com.bu.fourLevel.service;

import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/11/30 12:46
 * @mark FourLevelEnglishService
 */
public interface FourLevelEnglishService {

    PageInfo<FourLevelEnglishDto> list(Integer pageNum, Integer pageSize);

}

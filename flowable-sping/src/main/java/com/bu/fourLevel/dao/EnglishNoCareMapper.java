package com.bu.fourLevel.dao;

import com.bu.fourLevel.dto.EnglishNoCareDto;
import com.bu.fourLevel.dto.FourLevelEnglishDto;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/12/5 11:14
 * @mark EnglisNoCareMapper
 */
public interface EnglishNoCareMapper {

    Integer addEnglishNoCare(EnglishNoCareDto englishNoCareDto);

    List<FourLevelEnglishDto> listEnglish();

    Integer deleteEnglishNoCare(EnglishNoCareDto noCareDto);
}

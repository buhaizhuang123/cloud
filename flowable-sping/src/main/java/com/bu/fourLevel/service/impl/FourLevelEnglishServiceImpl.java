package com.bu.fourLevel.service.impl;

import com.bu.fourLevel.dao.FourLevelEnglishMapper;
import com.bu.fourLevel.dto.EnglishNoCareDto;
import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.bu.fourLevel.service.FourLevelEnglishService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

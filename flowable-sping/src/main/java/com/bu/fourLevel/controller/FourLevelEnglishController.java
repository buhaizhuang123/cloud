package com.bu.fourLevel.controller;

import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.bu.fourLevel.service.FourLevelEnglishService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haizhuangbu
 * @date 2023/11/30 12:41
 * @mark FourLevelEnglishController
 */
@RestController
@RequestMapping("/english")
public class FourLevelEnglishController {


    @Autowired
    private FourLevelEnglishService fourLevelEnglishService;

    @RequestMapping("/list")
    public PageInfo<FourLevelEnglishDto> listFourLevelEnglish(Integer pageNum, Integer pageSize) {
        return fourLevelEnglishService.list(pageNum, pageSize);
    }

}

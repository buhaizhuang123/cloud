package com.bu.fourLevel.controller;

import com.bu.fourLevel.dto.EnglishNoCareDto;
import com.bu.fourLevel.dto.FourLevelEnglishDto;
import com.bu.fourLevel.service.FourLevelEnglishNoCareService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/12/5 11:10
 * @mark FourLevelNoCareController
 */
@RestController
@RequestMapping("/fourLevelNoCare")
public class FourLevelNoCareController {

    @Autowired
    private FourLevelEnglishNoCareService fourLevelEnglishNoCareService;

    @RequestMapping(value = "/addEnglish", method = RequestMethod.POST)
    public Integer addEnglishNoCare(@RequestBody EnglishNoCareDto noCareDto) {
        return fourLevelEnglishNoCareService.addEnglishNoCare(noCareDto);
    }

    @RequestMapping(value = "/listNoCare", method = RequestMethod.GET)
    public PageInfo<FourLevelEnglishDto> list(Integer pageNum, Integer pageSize) {
        return fourLevelEnglishNoCareService.listEnglish(pageNum, pageSize);
    }

    @RequestMapping(value = "/deleteEnglish", method = RequestMethod.POST)
    public Integer deleteEnglishNoCare(@RequestBody EnglishNoCareDto noCareDto) {
        return fourLevelEnglishNoCareService.deleteEnglishNoCare(noCareDto);
    }


}

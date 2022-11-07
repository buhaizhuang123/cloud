package com.cloud.dict.controller;

import com.cloud.dict.dto.Dict;
import com.cloud.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/10/26 20:20
 * @mark DictController
 */
@RestController
@RequestMapping("dict")
public class DictController {
    @Autowired
    private DictService dictService;

    @RequestMapping(value = "query",method = RequestMethod.GET)
    public List<Dict> queryAllDict() {
        return dictService.query();
    }


}

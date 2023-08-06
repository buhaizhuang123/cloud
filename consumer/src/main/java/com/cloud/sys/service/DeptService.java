package com.cloud.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.cloud.common.SComCde;
import com.cloud.sys.dto.SDeptDo;
import com.cloud.sys.vo.DeptVo;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:07
 * @mark DeptService
 */
public interface DeptService {


    /**
     * @param sDeptDo 机构信息
     */
    void saveDept(SDeptDo sDeptDo);

    /**
     * @param deptVo 检索条件
     * @return 机构信息
     */
    List<SDeptDo> listDept(DeptVo deptVo);


    List<SComCde> listDistinctDept();


}

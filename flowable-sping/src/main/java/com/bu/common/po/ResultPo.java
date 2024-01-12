package com.bu.common.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2023/6/15 08:38
 * @mark ResultPo
 */
@Data
public class ResultPo {

    public ResultPo() {
    }

    private JSONObject data = new JSONObject();


    public ResultPo put(String key, Object val) {
        data.put(key, val);
        return this;
    }
}
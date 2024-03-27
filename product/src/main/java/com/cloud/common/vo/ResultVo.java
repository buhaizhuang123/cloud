package com.cloud.common.vo;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2024/1/17 15:59
 * @mark ResultVo
 */
@Data
public class ResultVo<T> {

    private Head head;

    private T body;

    @Data
    public static class Head {

        private String ec;

        private String em;
    }


    public static <T> ResultVo success(T body) {
        ResultVo<Object> vo = new ResultVo<>();
        Head head = new Head();
        head.setEc("0000");
        head.setEm("success");
        vo.setHead(head);
        vo.setBody(body);
        return vo;
    }

}

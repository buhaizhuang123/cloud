package com.cloud.common;

import lombok.Data;

/**
 * @author haizhuangbu
 * @date 2022/6/6 16:16
 * @mark Page
 */
public class Page {

    private final Integer DFL_NUM = 0;

    private final Integer DFL_SIZE = 10;

    private Integer pageNum;

    private Integer pageSize;

    {
        this.pageNum = DFL_NUM;

        this.pageSize = DFL_SIZE;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

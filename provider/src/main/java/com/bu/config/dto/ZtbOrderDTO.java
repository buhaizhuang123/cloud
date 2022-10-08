package com.bu.config.dto;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/10/8 15:15
 * @mark ZtbOrderDTO
 */
public class ZtbOrderDTO {

    private String courseName;

    private String orderNo;

    private BigDecimal price;

    private String id;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

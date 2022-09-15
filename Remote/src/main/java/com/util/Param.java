package com.util;

import java.math.BigDecimal;

/**
 * @author haizhuangbu
 * @date 2022/9/15 08:40
 * @mark Param
 */
public class Param {

    // 长度
    private Integer length;

    // 值
    private BigDecimal value;

    // 小数点整数位
    private Integer head;

    // 小数位
    private Integer tail;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public Integer getTail() {
        return tail;
    }

    public void setTail(Integer tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "Param{" +
                "length=" + length +
                ", value=" + value +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}

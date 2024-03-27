package com.cloud.person.dao;

import org.apache.ibatis.annotations.Insert;

import java.util.Date;

/**
 * @author haizhuangbu
 * @date 2024/3/27 09:55
 * @mark S1Mapper
 */
public interface S1Mapper {

    @Insert("insert into s1 values (#{0})")
    Integer insert(String data);

}

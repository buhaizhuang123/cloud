package com.cloud.person.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * @author haizhuangbu
 * @date 2024/3/27 09:55
 * @mark S1Mapper
 */
public interface S2Mapper {

    @Insert("insert into s2 values (#{0})")
    Integer insert(String data);

}

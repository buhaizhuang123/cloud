package com.cloud.sys.dao;

import org.apache.ibatis.annotations.Param;

/**
 * @author haizhuangbu
 * @date 2022/6/12 12:20
 * @mark WorldCountMapper
 */
public interface WorldCountMapper {

    Integer findCountByName(@Param("name") String name);

    void updateByName(@Param("name") String name, @Param("count") Integer count);

    Integer insert(@Param("name") String name, @Param("count") Integer count);


}

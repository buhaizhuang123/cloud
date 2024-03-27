package com.bu.fourLevel.dao;

import org.apache.ibatis.annotations.Insert;

/**
 * @author haizhuangbu
 * @date 2024/3/8 14:32
 * @mark TbTransMapper
 */
public interface TbTransMapper {


    @Insert("insert into tb_trans values (#{id})")
    Integer insertTransInfo(String id);

}

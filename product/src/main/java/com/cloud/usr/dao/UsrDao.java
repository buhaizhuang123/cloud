package com.cloud.usr.dao;

import com.cloud.usr.dto.Usr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/29 09:43
 * @mark UsrDao
 */
@Mapper
public interface UsrDao {
    List<Usr> listUsr(RowBounds rowBounds);
}

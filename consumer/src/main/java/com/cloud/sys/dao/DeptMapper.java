package com.cloud.sys.dao;

import com.cloud.common.SComCde;
import com.cloud.sys.dto.SDeptDo;
import com.cloud.sys.vo.DeptVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2023/8/5 21:04
 * @mark DeptMapper
 */
public interface DeptMapper {

    void save(SDeptDo sDeptDo);

    List<SDeptDo> listDept(DeptVo deptVo, RowBounds rowBounds);

    List<SComCde> list();

}

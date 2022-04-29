package com.cloud.usr.service.impl;

import com.cloud.usr.dao.UsrDao;
import com.cloud.usr.dto.Usr;
import com.cloud.usr.service.UsrService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2022/4/29 09:43
 * @mark UsrServiceImpl
 */
@Service
public class UsrServiceImpl implements UsrService {

    @Autowired
    public UsrDao usrDao;

    /**
     * @author haizhuang.bu
     * @date 09:44 2022/4/29
     * @function 返回所有用户
     */
    @Override
    public List<Usr> listUsr(){
        RowBounds rowBounds = new RowBounds(0,10);
        return usrDao.listUsr(rowBounds);
    }

}

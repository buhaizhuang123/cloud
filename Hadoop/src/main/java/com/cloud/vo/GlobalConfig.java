package com.cloud.vo;

import java.io.Serializable;

/**
 * @author haizhuangbu
 * @date 2022/6/11 16:43
 * @mark GlobalConfig
 */
public class GlobalConfig implements Serializable {


    private static final long serialVersionUID = -5908366370545056590L;

    /**
     * 驱动
     */
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";


    public static final String DB_URL = "jdbc:mysql://localhost:3306/firstDoc";

    public static final String USER_NAME = "bhz";

    public static final String PASSWORD = "Bhz123456";


}

//package com.cloud.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//
///*
// * @author haizhuangbu
// * @date 2023/4/18 09:44
// * @mark RouterDataSource
// */
//@Component
//public class RouterDataSource extends AbstractRoutingDataSource {
//
//    @Autowired
//    private DataSource dataSource;
//
//    /**
//     * 设置数据源
//     */
//    @Override
//    protected Object determineCurrentLookupKey() {
//        return dataSource;
//    }
//
//
//}

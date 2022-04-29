package com.cloud.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * @author haizhuangbu
 * @date 2022/4/28 09:29
 * @mark WebConfig
 */
@Component
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select USER_NAME,USER_PASS,USER_ENABLE\n" +
                        "from S_USR where USER_NAME = ?")
                .authoritiesByUsernameQuery("\n" +
                        "select AUTH_ROLE,S_AUTH.USER_NAME\n" +
                        "from S_AUTH where S_AUTH.USER_NAME = ?")
                .passwordEncoder(new MyPasswordEncoder())
                .rolePrefix("ADMIN");

//
//        auth.inMemoryAuthentication()
//                // 设置加密解密方式
//                .passwordEncoder(new MyPasswordEncoder())
//                // 用户名称
//                .withUser("spring")
//                // 用户密码
//                .password("password")
//                // 用户角色
//                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}

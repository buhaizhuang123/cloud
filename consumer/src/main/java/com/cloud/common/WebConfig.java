package com.cloud.common;

import com.cloud.config.CustomAuthProvider;
import com.cloud.sys.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.HashMap;


/**
 * @author haizhuangbu
 * @date 2022/4/28 09:29
 * @mark WebConfig
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomAuthProvider customAuthProvider;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select USER_NAME,USER_PASS,USER_ENABLE\n" +
//                        "from S_USR where USER_NAME = ?")
//                .authoritiesByUsernameQuery("\n" +
//                        "select AUTH_ROLE,S_AUTH.USER_NAME\n" +
//                        "from S_AUTH where S_AUTH.USER_NAME = ?")
//                .passwordEncoder(new MyPasswordEncoder())
//                .rolePrefix("ADMIN");
//
////
////        auth.inMemoryAuthentication()
////                // 设置加密解密方式
////                .passwordEncoder(new MyPasswordEncoder())
////                // 用户名称
////                .withUser("spring")
////                // 用户密码
////                .password("password")
////                // 用户角色
////                .roles("USER");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置缓存中的对象 实际中很少用
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("bhz")
                .password("123456")
                .authorities("read")
                .build();
        userDetailsManager.createUser(userDetails);
        // 设置加密与验证方式
        auth.userDetailsService(userDetailsManager)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

        //
        auth.authenticationProvider(customAuthProvider);

        // 数据库实现
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select USER_NAME,USER_PASS,USER_ENABLE\n" +
                        "from S_USR where USER_NAME = ?")
                .authoritiesByUsernameQuery("\n" +
                        "select AUTH_ROLE,S_AUTH.USER_NAME\n" +
                        "from S_AUTH where S_AUTH.USER_NAME = ?")
                .passwordEncoder(new MyPasswordEncoder())
                .rolePrefix("ADMIN");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests()
                .mvcMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
//
//    /**
//     * userDetailsService
//     */
//    @Override
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        UserDetails userDetails = User.withUsername("bhz")
//                .password("123456")
//                .authorities("read")
//                .build();
//        manager.createUser(userDetails);
//        return manager;
//    }
//
//    /**
//     * 设置加密方式
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}

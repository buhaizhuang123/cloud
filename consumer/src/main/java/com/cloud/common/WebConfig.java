package com.cloud.common;

import com.cloud.config.filter.InitialAuthenticationFilter;
import com.cloud.config.filter.JwtAuthenticationFilter;
import com.cloud.config.handler.CustAuthFailHandler;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.provider.OptAuthProvider;
import com.cloud.config.provider.UsernamePasswordAuthProvider;
import com.cloud.sys.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.Arrays;


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
    private OptAuthProvider optAuthProvider;
    @Autowired
    private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Autowired
    private CustAuthSuccHandler authenticationSuccessHandler;
    @Autowired
    private CustAuthFailHandler authenticationFailureHandler;
    @Autowired
    private InitialAuthenticationFilter initialAuthenticationFilter;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
        auth.authenticationProvider(optAuthProvider)
                .authenticationProvider(usernamePasswordAuthProvider);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单验证
        http.authorizeRequests()
                .mvcMatchers("image/**", "/error")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
//                .and().csrf().disable();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}

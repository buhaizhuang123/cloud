package com.cloud.common;

import com.cloud.config.filter.CkCodeAuthenticationFilter;
import com.cloud.config.filter.InitialAuthenticationFilter;
import com.cloud.config.filter.JwtAuthenticationFilter;
import com.cloud.config.handler.CustAuthFailHandler;
import com.cloud.config.handler.CustAuthSuccHandler;
import com.cloud.config.provider.OptAuthProvider;
import com.cloud.config.provider.UsernamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


/**
 * @author haizhuangbu
 * @date 2022/4/28 09:29
 * @mark WebConfig
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

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
    @Autowired
    private CkCodeAuthenticationFilter ckCodeAuthenticationFilter;

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
//        http.oauth2Client();


        // 表单验证
        http.csrf().disable().authorizeRequests()
                .mvcMatchers("image/**", "/error", "/file/**", "/login")
                .permitAll()
                // 删除功能 只有具备删除权限的用户才能操作
//                .mvcMatchers("/^del*")
//                .hasAnyAuthority("hasAuthority('delete')")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(ckCodeAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        // 默认表单验证 返回信息封装
        http.httpBasic((c) -> {
            c.authenticationEntryPoint((request, response, err) -> {
                response.setHeader("message", "I am Error");
                response.sendError(HttpStatus.UNAUTHORIZED.value());
            });
        });

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

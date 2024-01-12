package com.cloud.config;

import com.cloud.sys.dto.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author haizhuangbu
 * @date 2022/6/3 13:25
 * @mark AuthServerProxy
 */
@Component
public class AuthServerProxy {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.server.base.url}")
    private String baseUrl;

    public void sendAuth(String username, String password) {
        // "/user/auth"
        String url = baseUrl;
        SUser sUser = new SUser();
        sUser.setUsername(username);
        sUser.setPassword(password);
        HttpEntity<SUser> entity = new HttpEntity<>(sUser);
        restTemplate.exchange(url, HttpMethod.GET,entity, Void.class);
    }

    public Boolean sendOTP(String username, String code) {
        //"/opt/check"
        String url = baseUrl ;
        SUser sUser = new SUser();
        sUser.setUsername(username);
        sUser.setCode(code);
        HttpEntity<SUser> entity = new HttpEntity<>(sUser);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);
        return response.getStatusCode().equals(HttpStatus.OK);

    }



    public static void main(String[] args) {
        long sum =  60 * 1000;
        System.out.println("sum = " + sum);
    }

}

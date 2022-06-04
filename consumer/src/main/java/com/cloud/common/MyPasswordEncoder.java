package com.cloud.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author haizhuangbu
 * @date 2022/4/28 09:36
 * @mark MyPasswordEncoder
 */
@Service
public class MyPasswordEncoder implements PasswordEncoder {

    private Logger log = LoggerFactory.getLogger(MyPasswordEncoder.class);

    @Override
    public String encode(CharSequence charSequence) {
        log.info("密码:{}",charSequence.toString());
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}

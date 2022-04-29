package com.cloud.common;


import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author haizhuangbu
 * @date 2022/4/28 09:36
 * @mark MyPasswordEncoder
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}

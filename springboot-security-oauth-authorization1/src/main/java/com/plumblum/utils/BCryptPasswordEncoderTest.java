package com.plumblum.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: cpb
 * @Date: 2019/1/10 09:58
 * @Description:
 */
public class BCryptPasswordEncoderTest {
    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode("root");//SHA-256加密算法
        System.out.println(encoderPassword);
        System.out.println(encoderPassword.length());
        boolean f = encoder.matches("root",encoderPassword);
        System.out.println(f);
    }
}

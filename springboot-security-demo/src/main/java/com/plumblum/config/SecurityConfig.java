package com.plumblum.config;

import com.plumblum.authentication.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Auther: cpb
 * @Date: 2019/1/9 16:43
 * @Description:
 */
@Configuration
@EnableWebSecurity //启动webSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //启动方法级别的权限验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

}

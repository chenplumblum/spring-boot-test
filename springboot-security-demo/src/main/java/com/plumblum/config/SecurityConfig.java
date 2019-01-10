package com.plumblum.config;

import com.plumblum.authentication.MyUserDetailsService;
import com.plumblum.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
//    http.formLogin()          // 定义当需要用户登录时候，转到的登录页面。
//            .and()
//        .authorizeRequests()    // 定义哪些URL需要被保护、哪些不需要被保护
//        .anyRequest()        // 任何请求,登录后可以访问
//        .authenticated();


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated() //任何请求,登录后可以访问
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll() //登录页面用户任意访问
                .and()
                .logout().permitAll(); //注销行为任意访问

    }

    //不过滤资管。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        使用 MD5 加密
//        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder(){
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return MD5Util.encode((String)rawPassword);
//            }
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
//            }});
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

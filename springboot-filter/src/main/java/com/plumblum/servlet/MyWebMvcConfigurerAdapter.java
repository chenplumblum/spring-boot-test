package com.plumblum.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @Auther: cpb
 * @Date: 2018/8/28 09:36
 * @Description:
 */
//拦截器配置类
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurationSupport {

    //  配置拦截器实体(很重要)
    @Bean
    MyInterceptor  myInterceptor(){
        return new MyInterceptor();
    }

//    配置拦截器url
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 用于添加拦截规则
        //excludePathPatterns 用于排除拦截
        registry.addInterceptor(myInterceptor()).addPathPatterns("/*");
//                .excludePathPatterns("/hello/getAll");
        super.addInterceptors(registry);

    }
    /**
     * 配置静态资源
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("配置不拦截静态资源");
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }




}

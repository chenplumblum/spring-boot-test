package com.plumblum.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Auther: cpb
 * @Date: 2018/8/2 14:08
 * @Description:
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "test1DS")
    @Qualifier("test1DS")
//  设置主数据库
    @Primary
    @ConfigurationProperties(prefix="test1.spring.datasource")
    public DataSource test1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2DS")
    @Qualifier("test2DS")
    @ConfigurationProperties(prefix="test2.spring.datasource")
    public DataSource test2DataSource(){
        return DataSourceBuilder.create().build();
    }



}

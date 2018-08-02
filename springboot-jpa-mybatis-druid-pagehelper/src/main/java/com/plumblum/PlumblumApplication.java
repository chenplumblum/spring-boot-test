package com.plumblum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.plumblum.dao")
public class PlumblumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlumblumApplication.class, args);
	}
}

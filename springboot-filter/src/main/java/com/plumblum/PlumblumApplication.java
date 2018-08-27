package com.plumblum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.plumblum.servlet")
public class PlumblumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlumblumApplication.class, args);
	}
}

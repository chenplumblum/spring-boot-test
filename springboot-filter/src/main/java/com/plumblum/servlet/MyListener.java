package com.plumblum.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;

/**
 * @Auther: cpb
 * @Date: 2018/8/27 17:18
 * @Description:
 */
@WebListener
@ServletComponentScan("com.plumblum.servlet")
public class MyListener implements ServletContextListener {

    private static Logger LOG = LoggerFactory.getLogger(MyListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("myListener 初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("myListener 销毁...");
    }
}

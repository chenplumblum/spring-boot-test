package com.plumblum.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: cpb
 * @Date: 2018/8/27 17:22
 * @Description:
 */
//过滤的请求不会经过controller层
@WebFilter(filterName="MyFilter",urlPatterns="/getAll/*")
public class MyFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     logger.info("初始化过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        logger.info("过滤到的请求--->"+requestURI);
    }

    @Override
    public void destroy() {

    }
}

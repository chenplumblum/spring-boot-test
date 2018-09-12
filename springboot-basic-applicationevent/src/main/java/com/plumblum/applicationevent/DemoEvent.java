package com.plumblum.applicationevent;

import org.springframework.context.ApplicationEvent;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/1
 * Time:19:51
 */
public class DemoEvent  extends ApplicationEvent {

    private static final Long serialVersionUID = 1l;

//    定义信息
    private String message;

//    继承重写构造方法
    public DemoEvent(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

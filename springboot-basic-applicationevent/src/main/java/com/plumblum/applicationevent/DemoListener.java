package com.plumblum.applicationevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:plumblum
 * Date:2018/9/1
 * Time:19:54
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {


//    对消息进行处理
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
         String message = demoEvent.getMessage();
         System.out.println(message+" 已接受的信息");
    }
}

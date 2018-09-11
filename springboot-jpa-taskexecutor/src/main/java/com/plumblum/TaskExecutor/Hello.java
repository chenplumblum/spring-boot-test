package com.plumblum.TaskExecutor;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Auther: cpb
 * @Date: 2018/8/31 13:55
 * @Description:
 */
@Component
public class Hello {



    @Async
    public void hello(String name){
        System.out.println(Thread.currentThread().getName()+name);
    }


}

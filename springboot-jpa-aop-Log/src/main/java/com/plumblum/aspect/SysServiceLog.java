package com.plumblum.aspect;

import java.lang.annotation.*;

/**
 * @Auther: cpb
 * @Date: 2018/8/30 08:45
 * @Description:
 */
//作用位置。
@Target({ElementType.PARAMETER, ElementType.METHOD})
//注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysServiceLog {

    /**
     * 操作类型，类型为1：增加，2：删除，3：查询，4：修改，5：其他
     * @return
     */
    String oprlogType() default "";
    /**
     * 操作备注
     * @return
     */
    String oprlogRemark() default "";

    //操作模块
    String oprlogModule() default "";

}

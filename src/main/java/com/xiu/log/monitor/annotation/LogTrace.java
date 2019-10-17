package com.xiu.log.monitor.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author:MR.Xiu
 * Description: 日志追踪注解
 * Date:Created in 5:55 下午 2019/9/9
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Documented
@Target({METHOD})
@Retention(RUNTIME)
public @interface LogTrace {
    /**
     * 日志信息
     *
     * @return
     */
    String message();


    /**
     * 是否打印日志
     *
     * @return
     */
    boolean isPrint() default true;
}

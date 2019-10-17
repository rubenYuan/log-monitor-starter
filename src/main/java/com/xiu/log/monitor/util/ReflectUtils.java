package com.xiu.log.monitor.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import java.lang.reflect.Method;

/**
 * Author:MR.Xiu
 * Description:
 * Date:Created in 10:22 上午 2019/9/13
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public class ReflectUtils {
    public static Method getMethod(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        MethodSignature interfaceMethod = (MethodSignature) joinPoint.getSignature();
        Class<?>[] parameterTypes = interfaceMethod.getParameterTypes();
        String name = interfaceMethod.getMethod().getName();
        Method method;
        try {
            method = target.getClass().getMethod(name, parameterTypes);
        }
        catch (Exception e) {
            throw new RuntimeException("JoinPoint get Method failed.", e);
        }
        return method;
    }
}

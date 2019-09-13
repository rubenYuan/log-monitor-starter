package com.xiu.log.monitor.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.xiu.log.monitor.annotation.LogTrace;
import com.xiu.log.monitor.util.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;

/**
 * Author:MR.Xiu
 * Description: 日志AOP切面
 * Date:Created in 8:05 下午 2019/9/10
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Component
@Aspect
@Slf4j
public class LogAopHandler extends HandlerInterceptorAdapter {

    /**
     * 切面
     */
    @Pointcut("execution(public * *(..)) && " + "@annotation(com.xiu.log.monitor.annotation.LogTrace)")
    public void logPoint() {
    }

    public LogAopHandler() {
    }

    /**
     * @param joinPoint
     * @return
     */

    @Around("logPoint()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] objects = joinPoint.getArgs();
        LogTrace logTrace = ReflectUtils.getMethod(joinPoint).getAnnotation(LogTrace.class);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String requestBody = null;
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                if (object == null) {
                    requestBody = null;
                } else if (object instanceof String) {
                    requestBody = (String) object;
                } else {
                    requestBody = JSONObject.toJSONString(object);
                }
            }
            queryString = requestBody;
        }
        if (logTrace.isPrint()) {
            long beginTime = System.currentTimeMillis();
            log.info("\n==={} 请求开始,参数|url: {}|uri: {}|method: {}|params: {}", logTrace.message(), url, uri, method, queryString);
            // result的值就是被拦截方法的返回值
            Object result = joinPoint.proceed();
            log.info("\n==={} 请求结束,耗时: {}ms|返回数据: {} ", logTrace.message(), (System.currentTimeMillis() - beginTime), JSONObject.toJSONString(result));
        }
        return null;
    }
}

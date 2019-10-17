package com.xiu.log.monitor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:MR.Xiu
 * Description: 配置加载类
 * Date:Created in 10:22 上午 2019/9/13
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public class PropertiesLoadUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesLoadUtil.class);

    private static ConcurrentHashMap<String, String> propertiesMap;


    /**
     * 加载配置文件
     *
     * @param propertyFileName 配置文件名称
     */
    public static void loadAllProperties(String propertyFileName) {
        try {
            Properties props = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            if (null == propertiesMap)
                propertiesMap = new ConcurrentHashMap<>();
            for (Object key : props.keySet()) {
                String keyStr = key.toString();
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            LOGGER.warn("配置文件加载失败，", e);
        }
    }


    /**
     * 获取具体配置
     *
     * @param name
     * @return
     */
    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }

    /**
     * 获取所有配置
     *
     * @return
     */
    public static ConcurrentHashMap<String, String> getAllProperty() {
        return propertiesMap;
    }
}

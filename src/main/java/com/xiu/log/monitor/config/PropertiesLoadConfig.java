package com.xiu.log.monitor.config;


import com.xiu.log.monitor.constant.SysConstant;
import org.springframework.beans.BeansException;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: Mr.xiu
 * @Date: 2019/9/4 00:06
 * @Description: 加载配置文件
 */
public class PropertiesLoadConfig {
    private static Map<String, String> propertiesMap = new HashMap<>();

    private static void processProperties(Properties props) throws BeansException {
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            try {
                propertiesMap.put(keyStr, new String(props.getProperty(keyStr).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            } catch (Exception e) {
                System.out.println(SysConstant.APP_NAME.concat("启动中，发生了异常！"));
            }
        }
    }

    /**
     * 加载配置文件
     * @param propertyFileName
     */
    public static void loadAllProperties(String propertyFileName) {
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(propertyFileName);
            processProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }
}

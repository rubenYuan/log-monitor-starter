package com.xiu.log.monitor.listener;

import com.xiu.log.monitor.constant.SysConstant;
import com.xiu.log.monitor.util.PropertiesLoadUtil;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Mr.xiu
 * @Date: 2019/9/4 00:02
 * @Description: 配置文件监听
 */
@Component
@Configuration
public class PropertiesStartUp implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        //启动配置
        init(SysConstant.APP_NAME, SysConstant.FILE_NAME);
    }

    /**
     * 初始化方法
     *
     * @param appId
     * @param filePathName
     */
    public static void init(String appId, String filePathName) {
        System.out.println(appId + "配置文件加载中......");
        PropertiesLoadUtil.loadAllProperties(filePathName);
        ConcurrentHashMap<String, String> map = PropertiesLoadUtil.getAllProperty();
        if (!map.isEmpty()) {
            for (String key : map.keySet()) {
                System.out.println("【 " + key + "】：" + map.get(key));
            }
        }
        System.out.println(appId + "配置文件加载完成");
    }
}

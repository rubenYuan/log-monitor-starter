package com.xiu.log.monitor.listener;


import com.xiu.log.monitor.config.PropertiesConfig;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @Author: Mr.xiu
 * @Date: 2019/9/4 00:02
 * @Description: 配置文件监听
 */
@Component
@Configuration
public class PropertiesStartUp  implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        //启动配置
        new PropertiesConfig();
    }
}

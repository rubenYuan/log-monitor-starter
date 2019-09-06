package com.xiu.log.monitor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:23
 * @Description: 基础Configuration
 */
@Component
@ConfigurationProperties(prefix = "fy-monitor")
@Data
public class BaseProperties {
    /**
     * 业务线
     */
    private String appId;
    /**
     * 项目环境
     */
    private String appEnv;
}

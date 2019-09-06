package com.xiu.log.monitor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: Mr.xiu
 * @Date: 2019/9/1 06:40
 * @Description: 邮件配置
 */
@Component
@ConfigurationProperties(prefix = "fy-monitor")
@Data
public class EmailProperties {
    /**
     * 是否邮件
     */
    private Boolean isEmail;

    /**
     * 邮件某人
     */
    private String emailUser;
}

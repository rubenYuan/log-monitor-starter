package com.xiu.log.monitor.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:31
 * @Description: 钉钉发送配置
 */
@Component
@ConfigurationProperties(prefix = "fy-monitor")
@Data
public class DingProperties implements Serializable {
    /**
     * 钉钉地址
     */
    private String dingUrl;

    /**
     * @ 人
     */
    private String dingMobile;

    /**
     * 是否开启钉钉
     */
    private Boolean isDing;

}

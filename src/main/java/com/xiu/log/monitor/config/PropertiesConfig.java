package com.xiu.log.monitor.config;

import com.xiu.log.monitor.constant.SysConstant;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Mr.xiu
 * @Date: 2019/9/4 00:40
 * @Description: 配置文件初始化
 */
@Data
public class PropertiesConfig {
    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);


    static  {
        PropertiesLoadConfig.loadAllProperties("config/fy-monitor.properties");
        LOGGER.info("\n<<<<<<<<<<<<<<<<<<<".concat(SysConstant.APP_NAME).concat("配置文件加载中").concat(">>>>>>>>>>>>\n")
                .concat("[app_id]:".concat(PropertiesLoadConfig.getProperty("fy-monitor.app-id"))
                        .concat("\n[app_env]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.app-env"))
                        .concat("\n[is-ding]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.is-ding"))
                        .concat("\n[ding-url]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.ding-url"))
                        .concat("\n[ding-mobile]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.ding-mobile"))
                        .concat("\n[is-email]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.is-email"))
                        .concat("\n[email-user]:").concat(PropertiesLoadConfig.getProperty("fy-monitor.email-user"))
                        .concat("\n<<<<<<<<<<<<<<<<<<<<".concat(SysConstant.APP_NAME.concat("配置文件,加载完成>>>>>>>>>>>>>>>>>")))
                ));
    }


    public final static String APP_ID = PropertiesLoadConfig.getProperty("fy-monitor.app-id");

    /**
     * 环境
     */
    public final static String APP_ENV = PropertiesLoadConfig.getProperty("fy-monitor.app-env");

    /**
     * 钉钉地址
     */
    public final static String DING_URL = PropertiesLoadConfig.getProperty("fy-monitor.ding-url");

    /**
     * 是否钉钉开启
     */
    public final static String IS_DING = PropertiesLoadConfig.getProperty("fy-monitor.is-ding");

    /**
     * 钉钉手机号
     */
    public final static String DING_MOBILE = PropertiesLoadConfig.getProperty("fy-monitor.ding-mobile");

    /**
     * 邮件
     */
    public final static String IS_EMAIL = PropertiesLoadConfig.getProperty("fy-monitor.is-email");

    /**
     * 邮件
     */
    public final static String EMAIL_USER = PropertiesLoadConfig.getProperty("fy-monitor.email-user");
}

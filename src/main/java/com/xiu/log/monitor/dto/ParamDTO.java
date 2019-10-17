package com.xiu.log.monitor.dto;

import com.xiu.log.monitor.util.PropertiesLoadUtil;

/**
 * Author:MR.Xiu
 * Description:
 * Date:Created in 6:45 下午 2019/9/23
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public class ParamDTO {

    public final static String APP_ID = PropertiesLoadUtil.getProperty("xiu-monitor.app-id");

    /**
     * 环境
     */
    public final static String APP_ENV = PropertiesLoadUtil.getProperty("xiu-monitor.app-env");

    /**
     * 钉钉地址
     */
    public final static String DING_URL = PropertiesLoadUtil.getProperty("xiu-monitor.ding-url");

    /**
     * 是否钉钉开启
     */
    public final static String IS_DING = PropertiesLoadUtil.getProperty("xiu-monitor.is-ding");

    /**
     * 钉钉手机号
     */
    public final static String DING_MOBILE = PropertiesLoadUtil.getProperty("xiu-monitor.ding-mobile");

    /**
     * 邮件
     */
    public final static String IS_EMAIL = PropertiesLoadUtil.getProperty("xiu-monitor.is-email");

    /**
     * 邮件
     */
    public final static String EMAIL_USER = PropertiesLoadUtil.getProperty("xiu-monitor.email-user");
}

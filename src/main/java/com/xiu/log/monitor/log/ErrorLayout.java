package com.xiu.log.monitor.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import com.alibaba.fastjson.JSONObject;
import com.xiu.log.monitor.config.PropertiesConfig;
import com.xiu.log.monitor.dto.AtMobile;
import com.xiu.log.monitor.dto.DingDTO;
import com.xiu.log.monitor.dto.DingText;
import com.xiu.log.monitor.util.EmailManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:40
 * @Description: 日志打印格式
 */
public class ErrorLayout extends LayoutBase<ILoggingEvent> {
    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorLayout.class);
    private static SimpleDateFormat log_time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String doLayout(ILoggingEvent event) {
        String appId = PropertiesConfig.APP_ID;
        Assert.hasText(appId, "The appId must have value");
        String appEnv = PropertiesConfig.APP_ENV;
        Assert.hasText(appEnv, "The appEnv must have value");
        try {
            StackTraceElement[] stackTraceElements = event.getCallerData();
            if (stackTraceElements != null && stackTraceElements.length > 0) {
                StackTraceElement stackTraceElement = stackTraceElements[0];
                //发送钉钉
                sendDing(event,stackTraceElement);
                //发送邮件
                sendEmail(event, stackTraceElement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 发送钉钉
     *
     * @param event 事件
     * @param stackTraceElement 堆栈
     */
    private void sendDing(ILoggingEvent event, StackTraceElement stackTraceElement) {
        String dingUrl = PropertiesConfig.DING_URL;
        String mobiles = PropertiesConfig.DING_MOBILE;
        Boolean dingStatus = Boolean.valueOf(PropertiesConfig.IS_DING);
        if (dingStatus) {
            Assert.hasText(dingUrl, "The dingUrl must have value");
            StringBuilder sb = new StringBuilder(256);
            sb.append("### **异常警告**");
            sb.append("\n > - 项目名称：".concat(PropertiesConfig.APP_ID));
            sb.append("\n > - 项目环境：".concat(PropertiesConfig.APP_ENV));
            sb.append("\n > - 错误信息：").append(event.getFormattedMessage());
            if (event.getThrowableProxy() != null) {
                sb.append("\n > - 异常原因:").append(event.getThrowableProxy().getClassName().concat(".")).append(event.getThrowableProxy().getMessage());
            }
            sb.append("\n > - 类方法名：").append(stackTraceElement.getClassName());
            sb.append(".".concat(stackTraceElement.getMethodName()));
            sb.append("\n > - 代码行数：").append(stackTraceElement.getLineNumber());
            sb.append("\n> ###### ").append(log_time_format.format(new Date())).append(" 发布\n");
            if (StringUtils.isNotEmpty(mobiles)) {
                List<String> list = Arrays.asList(mobiles.split(","));
                list.forEach(p -> {
                    sb.append("@".concat(p).concat(""));
                });
            }
            packDingDto(sb.toString(), dingUrl, mobiles);
        }
    }

    /**
     * 发送邮件
     *
     * @param event             事件
     * @param stackTraceElement 堆栈
     */
    private void sendEmail(ILoggingEvent event, StackTraceElement stackTraceElement) {
        String emailUser = PropertiesConfig.EMAIL_USER;
        Boolean emailStatus = Boolean.valueOf(PropertiesConfig.IS_EMAIL);
        if (emailStatus) {
            Assert.hasText(emailUser, "The emailUser must have value");
            StringBuilder sb = new StringBuilder(500);
            sb.append("<div style='font-size:20px;color:red'>【异常警告】</div>");
            sb.append("<br/><b>项目名称：</b>".concat(PropertiesConfig.APP_ID));
            sb.append("<br/><b>项目环境：</b>".concat(PropertiesConfig.APP_ENV));
            sb.append("<br/><b>错误信息：</b>").append(event.getFormattedMessage());
            if (event.getThrowableProxy() != null) {
                sb.append("<br/><b>异常原因：</b>").append(event.getThrowableProxy().getClassName()).append(event.getThrowableProxy().getMessage());
            }
            sb.append("<br/><b> 类方法名：</b>").append(stackTraceElement.getClassName());
            sb.append(".".concat(stackTraceElement.getMethodName()));
            sb.append("<br/><b>代码行数：</b>").append(stackTraceElement.getLineNumber());
            sb.append("<br/>").append(log_time_format.format(new Date())).append(" 发布");
            String fileName = PropertiesConfig.APP_ID.concat("项目警报日志");
            EmailManager.send(emailUser, null, fileName, sb.toString(), null);
        }
    }

    /**
     * 钉钉发送d打包
     * @param msg 信息
     * @param dingUrl URL
     * @param mobiles 手机号
     */
    private void packDingDto( String msg, String dingUrl, String mobiles) {
        DingText dingText = new DingText();
        dingText.setText(msg);
        dingText.setTitle("#".concat(PropertiesConfig.APP_ID));
        DingDTO dingDTO = new DingDTO();
        dingDTO.setMsgtype("markdown");
        dingDTO.setMarkdown(dingText);
        if (StringUtils.isNotEmpty(mobiles)) {
            List<String> list = Arrays.asList(mobiles.split(","));
            AtMobile atMobile = new AtMobile();
            atMobile.setIsAtAll(Boolean.FALSE);
            atMobile.setAtMobiles(list);
            dingDTO.setAt(atMobile);
        }
        restTemplate.postForEntity(dingUrl, JSONObject.toJSON(dingDTO), JSONObject.class).getBody();
    }
}

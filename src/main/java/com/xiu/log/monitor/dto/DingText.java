package com.xiu.log.monitor.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:44
 * @Description: 钉钉发送内容
 */
@Data
public class DingText implements Serializable {
    private String title;
    private String text;
}

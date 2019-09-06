package com.xiu.log.monitor.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:42
 * @Description: 钉钉DTO
 */
@Data
public class DingDTO implements Serializable {
    /**
     * 类型
     */
    private String msgtype;

    /**
     * 文档
     */
    private DingText markdown;

    /**
     * @某人
     */
    private AtMobile at;
}
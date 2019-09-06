package com.xiu.log.monitor.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Mr.xiu
 * @Date: 2019/8/31 14:42
 * @Description: @人
 */
@Data
public class AtMobile implements Serializable {
    private List<String> atMobiles;
    private Boolean isAtAll;
}

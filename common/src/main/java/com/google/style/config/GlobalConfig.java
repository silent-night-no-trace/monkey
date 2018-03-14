package com.google.style.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liangz
 * @date 2018/3/12 16:13
 * 全局配置 信息
 **/
@Data
@Component
@ConfigurationProperties(prefix="Style")
public class GlobalConfig {
    /**
     * 上传路径
     */
    private String uploadPath;

}

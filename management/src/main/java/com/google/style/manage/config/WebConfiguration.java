package com.google.style.manage.config;

import com.google.style.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liangz
 * @date 2018/3/20 17:27
 * 自定义 资源文件映射
 **/
@Component
public class WebConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private GlobalConfig globalConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**").addResourceLocations("file:///"+globalConfig.getUploadPath());
    }
}

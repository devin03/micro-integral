package com.devin.micro.integral.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 * @author wangdongming
 * @date 2020/08/16
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private AppPropertiesConfig appConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler为前端url访问路径，addResourceLocations为本地磁盘映射，注：路径结尾要加 /
        registry.addResourceHandler(appConfig.getShowUrl()).addResourceLocations("file:" + appConfig.getUploadUrl());
    }
}
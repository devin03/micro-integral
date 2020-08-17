package com.devin.micro.integral.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 配置文件
 * @author wangdongming
 * @date 2020/08/16
 */
@Service
@RefreshScope
@ConfigurationProperties(prefix = "app.conf")
public class AppPropertiesConfig {

    /**
     * #文件上传路径
     */
    private String uploadUrl;
    /**
     * #文件回显路径
     */
    private String showUrl;

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }
}

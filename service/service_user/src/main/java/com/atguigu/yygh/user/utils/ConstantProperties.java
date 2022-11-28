package com.atguigu.yygh.user.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:wx.properties")
@ConfigurationProperties("wx.open") //读取节点
public class ConstantProperties {
    private String appId;
    private String appSecret;
    private String redirectUri;
}

package com.atguigu.yygh.oss.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource("classpath:application.properties") //读取配置文件（默认文件可省略）
@ConfigurationProperties(prefix="aliyun.oss") //读取节点
@Data
public class ConstantProperties {

    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}

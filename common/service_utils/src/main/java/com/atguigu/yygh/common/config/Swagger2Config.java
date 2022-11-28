package com.atguigu.yygh.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminConfig(){
        //创建swagger文档
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了后台管理系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("atguigu", "http://atguigu.com", "123@qq.com"))
                .build();
    }

    @Bean
    public Docket apiConfig(){
        //创建swagger文档
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api")
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }


    @Bean
    public Docket frontConfig(){
        //创建swagger文档
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("front")
                .select()
                //只显示front路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/front/.*")))
                .build();
    }

    @Bean
    public Docket innerConfig(){
        //创建swagger文档
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("inner")
                .select()
                //只显示inner路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/inner/.*")))
                .build();
    }
}

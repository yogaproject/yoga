package com.woniu.yoga.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @Description swagger配置类
 * @Author guochxi
 * @Date 2019/4/23 17:22
 * @Version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        System.out.println("***********************");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.woniu.yoga"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("yogaAPP-api文档")
                .description("yoga")
                .termsOfServiceUrl("localhost:80/")
                .version("1.0")
                .build();
    }
}

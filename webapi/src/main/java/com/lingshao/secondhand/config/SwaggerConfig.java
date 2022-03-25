package com.lingshao.secondhand.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LingShao
 * @date 2021/4/18
 * @desc: swagger 接口管理配置类
 ***/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以注入多个doket，也就是多个版本的api，可以在看到有三个版本groupName不能是重复的，v1和v2是ant风格匹配，配置文件
     * @return
     */
    @Bean
    public Docket createRestApi() {
        //可以添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                .parameterType("header")
                .name("Authorization")
                .description("header中Authorization字段用于认证")
                .modelRef(new ModelRef("string"))
                //非必需，这里是全局配置，然而在登录的时候是不用验证的
                .required(false).build();
        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).groupName("v1").select()
                .apis(RequestHandlerSelectors.basePackage("com.lingshao.secondhand.controller"))
                .paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo1()).globalOperationParameters(aParameters);
    }


    private ApiInfo apiInfo1() {
        return new ApiInfoBuilder()
                .title("校园二手交易市场 APIs")
                .contact("灵少")
                .version("v1.0")
                .build();
    }
}

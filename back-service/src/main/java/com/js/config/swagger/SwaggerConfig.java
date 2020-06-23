package com.js.config.swagger;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: MoXingwang 2018-07-16 16:53
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.basePackage("com.js.controller")).paths(PathSelectors.any())
            .paths(Predicates.not(PathSelectors.regex("/error.*")))
            // 错误路径不监控
            .paths(PathSelectors.regex("/.*"))
            // 对根下所有路径进行监控
            .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中） 访问地址：http://项目实际地址/swagger-ui.html
     * 
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("BackOaProject平台").description("传统OA")
            .contact(new Contact("JS", "http://oa.hdgbt.com", "2545251075@qq.com")).version("1.0").build();
    }
}

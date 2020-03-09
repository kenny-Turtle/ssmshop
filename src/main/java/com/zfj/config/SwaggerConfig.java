package com.zfj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zfj
 * @create 2020/3/2 15:10
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket myDocket(){

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("商城项目的Swagger API文档")
                .description("Description: If you want to learn more, you can visit my GitHub website: https://github.com/kenny-Turtle/Tmall_SSM")
                .contact("jay")
                .version("v1.0")
                .build();
        docket.apiInfo(apiInfo);
        docket.select().apis(RequestHandlerSelectors.basePackage("com.zfj.controller")).build();
        return docket;

    }

}

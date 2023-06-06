package com.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE_NAME: com.zdh.core.config
 * @NAME: SwaggerConfig
 * @Author: HeYuTong
 * @DATE: 2021/8/23
 **/
@EnableSwagger2
@Component
@Configuration
public class SwaggerConfig {
        @Bean
        public Docket api(){
            List<Parameter> pars = new ArrayList<>();
            return new Docket(DocumentationType.SWAGGER_2).groupName("openai")
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build()
                    .globalOperationParameters(pars)
                    .apiInfo(apiInfo());
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("微服务后端接口文档")
                    .description("供前后端交互及测试内部使用")
                    .version("V2.1.0")
                    .termsOfServiceUrl("")
                    //.license("LICENSE")
                    //.licenseUrl("http://url-to-license.com")
                    .build();
        }
}

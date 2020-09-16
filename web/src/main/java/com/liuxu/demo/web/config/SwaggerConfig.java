package com.liuxu.demo.web.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customImplementation() {

        ApiInfo apiInfo = new ApiInfoBuilder().title("SpringBootDemo").description("").license("").licenseUrl("")
                .termsOfServiceUrl("").version("1.0.0").build();
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select()
                .paths(Predicates.not(PathSelectors.regex("/error.*"))).build().apiInfo(apiInfo);
    }
}

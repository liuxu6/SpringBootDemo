package com.liuxu.demo.web.config;


import com.liuxu.demo.common.constant.CommonDef;
import com.liuxu.demo.service.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor localInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor).addPathPatterns(CommonDef.BASE_PATH + "/**").excludePathPatterns("/error", CommonDef.BASE_PATH + "/**/login/**");
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index.html");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}

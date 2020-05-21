package com.liuxu.web.config;


import com.liuxu.common.constant.CommonDef;
import com.liuxu.service.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * <Description> <br>
 * 
 * @author wu.bo3<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年12月17日<br>
 * @since <br>
 * @see <br>
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    AuthenticationInterceptor localInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor).addPathPatterns(CommonDef.BASE_PATH + "/**").excludePathPatterns("/error", CommonDef.BASE_PATH + "/**/login/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}

package com.Springboot_web_rest.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    TokenValidationInterceptor tokenValidator;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(tokenValidator);//passing the Interceptor object.

    }
}

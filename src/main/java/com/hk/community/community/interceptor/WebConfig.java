package com.hk.community.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 作者: 何康先生
 * 时间: 2020-03-24 22:32
 * 描述:
 **/
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    String webPath;

    @Value("${local.upload-path}")
    String localPath;
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(webPath+"**").addResourceLocations("file:"+localPath);
    }
}

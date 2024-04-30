package com.congwei.sessionlogin.config;

import com.congwei.sessionlogin.interceptor.AccessInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AccessConfig implements WebMvcConfigurer {

    @Resource
    private AccessInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")         // 拦截所有请求
                .excludePathPatterns("/login"); // /login请求排除
    }
}

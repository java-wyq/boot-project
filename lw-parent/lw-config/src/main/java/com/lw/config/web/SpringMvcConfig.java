package com.lw.config.web;

import com.lw.config.interceptor.AccessLimitInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 通用配置
 *
 * @author ghouse
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AccessLimitInterceptor())
                .addPathPatterns("/**/app/**")
                .excludePathPatterns("/swagger-ui.html/**");

    }
}
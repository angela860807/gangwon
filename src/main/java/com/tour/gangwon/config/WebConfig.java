package com.tour.gangwon.config;

import com.tour.gangwon.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/notice/**")
                .excludePathPatterns(
                        "/",
                        "/notice/list",
                        "/notice/detail/**",
                        "/member/login",
                        "/member/logout",
                        "/member/terms",
                        "/member/register",
                        "/member/check-userid",
                        "/sub/**",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**"
                );
    }
}

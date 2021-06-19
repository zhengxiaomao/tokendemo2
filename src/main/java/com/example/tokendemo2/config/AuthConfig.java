package com.example.tokendemo2.config;

import com.example.tokendemo2.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor initAuthInterceptor(){
        return new AuthInterceptor();
    }

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/test/**").excludePathPatterns("/login/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/test");
    }

}
package com.example.myMusic.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myMusic.common.interceptor.TokenInterceptor;



@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", " HEAD", "OPTIONS", "TRACE")
			.allowCredentials(true)
			.maxAge(3600);
		
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 拦截所有请求
		registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
		
	}

}

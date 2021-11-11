package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptors.CallRequestInterceptor;

@Configuration
public class WebAppInitializer implements WebMvcConfigurer {

	@Autowired
	private CallRequestInterceptor requestLoggingInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(requestLoggingInterceptor);
	}

}

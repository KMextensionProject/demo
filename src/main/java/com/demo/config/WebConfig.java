package com.demo.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.demo.security.SecurityConfig;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
			AppConfig.class,
			SecurityConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// handled by the root config class
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		super.onStartup(context);
	}

}

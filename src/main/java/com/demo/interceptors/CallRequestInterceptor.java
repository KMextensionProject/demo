package com.demo.interceptors;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CallRequestInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getAnonymousLogger();

	private long start;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
		start = System.currentTimeMillis();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav) {
		// called before generating the view
		long end = System.currentTimeMillis();
		String url = String.valueOf(req.getRequestURL());
		logger.info(() -> "Calling of " + url + " took " + (end - start) + "ms");
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
		// called after generating the view
		// do nothing yet
	}

}

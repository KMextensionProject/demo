package com.demo.services;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

	/**
	 * @service /ping
	 * implementation of the logical service
	 */
	public String ping() {
		return "Status OK";
	}

}

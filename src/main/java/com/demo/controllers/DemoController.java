package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.services.DemoService;

@Controller
public class DemoController {

	@Autowired
	private DemoService demoService;
	
	@GetMapping(path = "/ping", produces = "text/plain")
	@ResponseBody
	public String ping() {
		return demoService.ping();
	}
}

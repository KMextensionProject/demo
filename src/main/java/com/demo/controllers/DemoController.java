package com.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.helpers.TypeMap;
import com.demo.security.user.DemoUser;
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
	
	@GetMapping(path = "/users", produces = "application/json")
	@ResponseBody
	public List<TypeMap> getUsers() {
		return demoService.getUsers();
	}
	
	// demo/users/?username=name
	@GetMapping(path = "/users/", produces = "application/json")
	@ResponseBody
	public TypeMap getUser(@RequestParam(required = true) String username) {
		return demoService.getUser(username);
	}
	
	@GetMapping("/users/excel")
	public void getUsersExcel(HttpServletResponse response) {
		demoService.getUsersExcel(response);
	}

}

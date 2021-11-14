package com.demo.services;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.enums.Resources;
import com.demo.helpers.TypeMap;
import com.demo.security.database.AuthJdbcTemplate;
import com.demo.security.user.DemoUser;
import com.demo.utils.XlsxUtils;

@Service
public class DemoService {

	@Autowired
	private AuthJdbcTemplate jdbcTemplate;

	/**
	 * @service /ping
	 * implementation of the logical service
	 */
	public String ping() {
		return "Status OK";
	}

	/**
	 * @service /users
	 * implementation of the logical service
	 */
	public List<TypeMap> getUsers() {
		return jdbcTemplate.getUsers().stream()
			.map(DemoUser::toTypeMap)
			.collect(toList());
	}
	
	/**
	 * @service /users/{username}
	 * implementation of the logical service
	 */
	public TypeMap getUser(String username) {
		return jdbcTemplate.getUserByUsername(username).toTypeMap();
	}

	/**
	 * 
	 */
	public void getUsersExcel(HttpServletResponse response) {
		try {
			List<TypeMap> userList = getUsers();
			TypeMap data = new TypeMap("usersTable", userList);
			XlsxUtils.generateXlsx(response, Resources.USERS_OVERVIEW_TEMPLATE, "Users.xlsx", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

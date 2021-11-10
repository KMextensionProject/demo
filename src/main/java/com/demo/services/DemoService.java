package com.demo.services;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.helpers.TypeMap;
import com.demo.security.database.AuthJdbcTemplate;
import com.demo.security.user.DemoUser;

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

}

package com.demo.security.database;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.demo.security.user.DemoUser;
import com.demo.security.user.DemoUserRowMapper;
// TODO: do tohto implementovat user cache
@Component
public class AuthJdbcTemplate extends JdbcTemplate {

	private static final String USER_AUTH_LEFT_JOIN = "SELECT A.*, B.authority FROM users A LEFT JOIN authorities B ON A.username = B.username";
	private static final String IS_ACTIVE = "A.enabled = 1";
	
	private static final String ALL_USERS_QUERY = USER_AUTH_LEFT_JOIN + " WHERE " + IS_ACTIVE;
	private static final String USER_BY_ID_QUERY = USER_AUTH_LEFT_JOIN + " WHERE A.id = ? AND " + IS_ACTIVE;
	private static final String USER_BY_USERNAME_QUERY = USER_AUTH_LEFT_JOIN + " WHERE A.username = ? AND " + IS_ACTIVE;

	@Autowired
	private DemoUserRowMapper userRowMapper;

	@Autowired
	public AuthJdbcTemplate(final DataSource dataSource) {
		super(dataSource);
	}

	public List<DemoUser> getUsers() {
		return query(ALL_USERS_QUERY, userRowMapper);
	}

	public DemoUser getUserById(int id) {
		return queryForObject(USER_BY_ID_QUERY, userRowMapper, id);
	}

	public DemoUser getUserByUsername(String username) {
		return queryForObject(USER_BY_USERNAME_QUERY, userRowMapper, username);
	}
}

package com.demo.security.database;

import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import static com.demo.security.user.DelegatingResultSetExtractor.allUsersExtractor;
import static com.demo.security.user.DelegatingResultSetExtractor.singleUserExtractor;

import com.demo.security.user.DemoUser;
// TODO: do tohto implementovat user cache
@Component
public class AuthJdbcTemplate extends JdbcTemplate {

	// lepsie definovat tieto konstanty... vyskladat pre to jednoduche api?? myslim ze nie...ale pouvazuj
	private static final String USER_AUTH_LEFT_JOIN = "SELECT A.*, B.authority FROM users A LEFT JOIN authorities B ON A.username = B.username";
	private static final String IS_ACTIVE = "A.enabled = 1";
	
	private static final String ALL_USERS_QUERY = USER_AUTH_LEFT_JOIN + " WHERE " + IS_ACTIVE;
	private static final String USER_BY_ID_QUERY = USER_AUTH_LEFT_JOIN + " WHERE A.id = ? AND " + IS_ACTIVE;
	private static final String USER_BY_USERNAME_QUERY = USER_AUTH_LEFT_JOIN + " WHERE A.username = ? AND " + IS_ACTIVE;

	@Autowired
	public AuthJdbcTemplate(final DataSource dataSource) {
		super(dataSource);
	}

	public Collection<DemoUser> getUsers() {
		return query(ALL_USERS_QUERY, allUsersExtractor());
	}

	public DemoUser getUserById(int id) {
		// there is no user id yet...also username must be unique
		return null;
	}

	@SuppressWarnings("deprecation")
	public DemoUser getUserByUsername(String username) {
		Object[] args = {username};
		return query(USER_BY_USERNAME_QUERY, args, singleUserExtractor());
	}
}

package com.demo.security.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class DelegatingResultSetExtractor {

	public static ResultSetExtractor<DemoUser> singleUserExtractor() {
		return resultSet -> (DemoUser) extractUsers(resultSet).toArray()[0];
	}

	public static ResultSetExtractor<Collection<DemoUser>> allUsersExtractor() {
		return DelegatingResultSetExtractor::extractUsers;
	}

	private static Collection<DemoUser> extractUsers(ResultSet rs) throws SQLException, DataAccessException {
		
		Map<String, DemoUser> usersByUsername = new HashMap<>();

		while (rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("password");

			DemoUser user = usersByUsername.get(username);

			if (user == null) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				user = new DemoUser(username, password, authorities);
				usersByUsername.put(username, user);
			}

			String role = rs.getString("authority");

			if (role != null && !role.isEmpty()) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				user.getAuthorities().add(authority);
			}
		}

		return usersByUsername.values();
	}
	
}

package com.demo.security.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DemoUserResultSetExtractor implements ResultSetExtractor<DemoUser> {

	/**
	 * This doesnt work!!!
	 * 
	 * it gives only one user with duplicate authorities
	 */
	@Override
	public DemoUser extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<GrantedAuthority> authorities = new ArrayList<>();

		rs.next();

		String username = rs.getString("username");
		String password = rs.getString("password");

		do {
			String authority = rs.getString("authority");
			if (authority != null && !authority.isEmpty()) {
				authorities.add(new SimpleGrantedAuthority(authority));
			}
		} while(rs.next());

		return new DemoUser(username, password, authorities);
	}

}

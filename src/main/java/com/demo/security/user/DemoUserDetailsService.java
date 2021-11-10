package com.demo.security.user;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.security.database.AuthJdbcTemplate;

@Component
public class DemoUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthJdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DemoUser user = jdbcTemplate.getUserByUsername(username);
		validateUser(user);
		return user;
	}

	private void validateUser(DemoUser user) {
		if (Objects.isNull(user) || Objects.isNull(user.getUsername())) {
			throw new UsernameNotFoundException("User doesn't exist.");
		}
	}
}

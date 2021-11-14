package com.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.security.user.DemoUserDetailsService;

@Component
public class DemoAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DemoUserDetailsService userDetailsService;

	@Override
	public Authentication authenticate (final Authentication authentication) {
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());

		UserDetails user = userDetailsService.loadUserByUsername(username);

		boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());
		if (!passwordMatches) {
			throw new BadCredentialsException("Wrong username or password");
		}

		return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
	}

	@Override
	public boolean supports (Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}

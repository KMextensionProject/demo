package com.demo.security.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:database.properties")
public class AuthDataSource extends DriverManagerDataSource {

	protected AuthDataSource(
			@Value("${auth_url}") final String auth_url,
			@Value("${auth_username}") final String auth_username,
			@Value("${auth_password}") final String auth_password,
			@Value("${auth_driver}") final String auth_driver) {

		super.setUrl(auth_url);
		super.setUsername(auth_username);
		super.setPassword(auth_password);
		super.setDriverClassName(auth_driver);
	}

}

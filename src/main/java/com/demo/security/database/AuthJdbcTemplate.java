package com.demo.security.database;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthJdbcTemplate extends JdbcTemplate {

	@Autowired
	public AuthJdbcTemplate(final DataSource dataSource) {
		super(dataSource);
	}

}

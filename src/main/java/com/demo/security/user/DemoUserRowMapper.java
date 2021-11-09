package com.demo.security.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DemoUserRowMapper implements RowMapper<DemoUser> {

	@Override
	public DemoUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}

}

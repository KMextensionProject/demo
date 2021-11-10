package com.demo.security.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class DemoUserRowMapper implements RowMapper<DemoUser> {

	@Autowired
	private DemoUserResultSetExtractor userExtractor;

	@Override
	public DemoUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		return userExtractor.extractData(rs);
	}

}

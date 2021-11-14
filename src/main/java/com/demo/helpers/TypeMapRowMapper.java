package com.demo.helpers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class TypeMapRowMapper implements RowMapper<TypeMap> {

	@Override
	public TypeMap mapRow(ResultSet rs, int rowNum) throws SQLException {

		// TODO: implement the columnNames retrieval effectively so it can be retrieved lazily
		List<String> columnNames = getColumnNames(rs);

		TypeMap data = new TypeMap();

		// we should not call next() on ResultSet as mentioned by the Doc.
		for (String columnName : columnNames) {
			data.put(columnName, rs.getObject(columnName));
		}

		return data;
	}

	private List<String> getColumnNames(ResultSet resultSet) throws SQLException {
		List<String> columnNames = new ArrayList<>();
		ResultSetMetaData rsMeta = resultSet.getMetaData();

		int columns = rsMeta.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			columnNames.add(rsMeta.getColumnName(i));
		}

		return columnNames;
	}
}

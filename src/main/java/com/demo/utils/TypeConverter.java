package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class TypeConverter {

	public String getAsString(Object object) {
		return object.toString();
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAsList(Object object) {
		if (object instanceof List) {
			return (List<Object>) object;
		} else {
			List<Object> list = new ArrayList<>();
			list.add(object);
			return list;
		}
	}

	public Integer getAsInteger(Object object) {
		if (object instanceof Number) {
			return Integer.valueOf((int) object);
		} else {
			return Integer.parseInt(object.toString());
		}
	}

	public Double getAsDouble(Object object) {
		if (object instanceof Number) {
			return Double.valueOf(object.toString());
		}
		return 0.0;
	}

	public Boolean getAsBoolean(Object object) {
		if (object instanceof Boolean) {
			return Boolean.valueOf(String.valueOf(object));
		}
		return null;
	}
}

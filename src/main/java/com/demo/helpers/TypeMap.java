package com.demo.helpers;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.formula.eval.NotImplementedException;

import com.demo.utils.TypeConverter;

import sk.loto.exceptions.UnexpectedDataCount;

public class TypeMap implements Map<String, Object> {

	private Map<String, Object> innerMap = new LinkedHashMap<>();
	private TypeConverter typeConverter = new TypeConverter();

	public TypeMap() {
		// ignore
	}

	public TypeMap(TypeMap map) {
		innerMap.putAll(map);
	}

	public TypeMap(Object...pairs) {
		validateDataCount(pairs);
		for (int i = 0; i < pairs.length; i += 2) {
			innerMap.put(pairs[i].toString(), pairs[i + 1]);
		}
	}

	private void validateDataCount(Object[] pairs) {
		if (pairs.length % 2 != 0) {
			throw new UnexpectedDataCount("TypeMapValidation", pairs.length);
		}
	}

	// TODO: test it
	public void renameKey(String key, String newKey) {
		Object value = innerMap.remove(key);
		innerMap.put(newKey, value);		
	}

	// TODO: implement level of depth..counting just maps and their values as maps and so on..
	public int getLevels() {
		return -1;
	}
	
	@Override
	public int size() {
		return innerMap.size();
	}

	@Override
	public boolean isEmpty() {
		return innerMap.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return innerMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return innerMap.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return innerMap.get(key);
	}

	public TypeMap getTypeMap(String key) {
		throw new NotImplementedException("should return TypeMap value if possible");
	}

	public List<Object> getList(String key) {
		return typeConverter.getAsList(innerMap.get(key));
	}

	public String getString(String key) {
		return typeConverter.getAsString(innerMap.get(key));
	}

	public Integer getInteger(String key) {
		return typeConverter.getAsInteger(innerMap.get(key));
	}

	public Double getDouble(String key) {
		return typeConverter.getAsDouble(innerMap.get(key));
	}

	public Boolean getBoolean(String key) {
		return typeConverter.getAsBoolean(innerMap.get(key));
	}

	@Override
	public Object put(String key, Object value) {
		return innerMap.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return innerMap.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		innerMap.putAll(map);
	}

	@Override
	public void clear() {
		innerMap.clear();
	}

	@Override
	public Set<String> keySet() {
		return innerMap.keySet();
	}

	@Override
	public Collection<Object> values() {
		return innerMap.values();
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return innerMap.entrySet();
	}

	@Override
	public String toString() {
		if (innerMap == null) {
			return "null";
		}
		return innerMap.toString();
	}

	@Override
	public int hashCode() {
		return innerMap.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return innerMap.equals(object);
	}
}
package com.demo.exceptions;

public class UnexpectedDataCount extends GeneralException {

	private static final long serialVersionUID = 1L;

	public UnexpectedDataCount(String message, Object... messageArguments) {
		super(message, messageArguments);
	}	
}

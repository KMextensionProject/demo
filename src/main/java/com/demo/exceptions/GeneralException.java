package com.demo.exceptions;

import com.demo.utils.MessageResolver;

public class GeneralException extends RuntimeException {

	private static final long serialVersionUID = -50378626526532631L;

	private String message;

	public GeneralException (String message, Object... messageArguments) {
		super();
		this.message = MessageResolver.resolveMessage(message, messageArguments);
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
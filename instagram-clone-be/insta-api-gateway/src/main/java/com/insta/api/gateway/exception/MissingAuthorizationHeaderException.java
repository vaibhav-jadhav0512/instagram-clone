package com.insta.api.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MissingAuthorizationHeaderException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2203598992805581684L;
	private Object[] args;

	public MissingAuthorizationHeaderException() {
		super();
	}

	public MissingAuthorizationHeaderException(String message) {
		super(message);
	}

	public MissingAuthorizationHeaderException(String message, Object[] args) {
		super(message);
		this.args = args;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}

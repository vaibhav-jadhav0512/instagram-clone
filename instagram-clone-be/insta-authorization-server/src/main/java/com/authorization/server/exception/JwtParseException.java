package com.authorization.server.exception;

public class JwtParseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908365898180895925L;

	private Object[] args;

	public JwtParseException() {
		super();
	}

	public JwtParseException(String message) {
		super(message);
	}

	public JwtParseException(String message, Object[] args) {
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

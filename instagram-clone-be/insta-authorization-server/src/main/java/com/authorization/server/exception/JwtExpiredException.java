package com.authorization.server.exception;

public class JwtExpiredException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6920092406329207226L;
	private Object[] args;

	public JwtExpiredException() {
		super();
	}

	public JwtExpiredException(String message) {
		super(message);
	}

	public JwtExpiredException(String message, Object[] args) {
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

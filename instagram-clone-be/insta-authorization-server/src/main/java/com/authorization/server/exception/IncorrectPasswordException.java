package com.authorization.server.exception;

public class IncorrectPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8577899974739138349L;
	private Object[] args;

	public IncorrectPasswordException() {
		super();
	}

	public IncorrectPasswordException(String message) {
		super(message);
	}

	public IncorrectPasswordException(String message, Object[] args) {
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

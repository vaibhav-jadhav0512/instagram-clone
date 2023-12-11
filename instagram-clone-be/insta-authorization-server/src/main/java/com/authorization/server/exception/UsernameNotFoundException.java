package com.authorization.server.exception;

public class UsernameNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 428017107816770454L;

	private Object[] args;

	public UsernameNotFoundException() {
		super();
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

	public UsernameNotFoundException(String message, Object[] args) {
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

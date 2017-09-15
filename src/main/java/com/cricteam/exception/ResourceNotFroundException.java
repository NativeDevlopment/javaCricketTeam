package com.cricteam.exception;

public class ResourceNotFroundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5418076839172569145L;

	public ResourceNotFroundException() {
		super();
	}

	public ResourceNotFroundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFroundException(final String message) {
		super(message);
	}

	public ResourceNotFroundException(final Throwable cause) {
		super(cause);
	}
}

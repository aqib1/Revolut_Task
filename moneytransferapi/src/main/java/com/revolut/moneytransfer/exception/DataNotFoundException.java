package com.revolut.moneytransfer.exception;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/11/2019
 * @see {@link RuntimeException}
 *
 */
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4798977083347296281L;

	public DataNotFoundException() {

	}

	/**
	 * @param message
	 */
	public DataNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public DataNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

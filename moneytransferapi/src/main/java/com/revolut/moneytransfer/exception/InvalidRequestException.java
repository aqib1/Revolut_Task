package com.revolut.moneytransfer.exception;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 * @see {@link RuntimeException}
 *
 */
public class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1887885009436263451L;

	public InvalidRequestException() {

	}

	/**
	 * @param message
	 */
	public InvalidRequestException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public InvalidRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

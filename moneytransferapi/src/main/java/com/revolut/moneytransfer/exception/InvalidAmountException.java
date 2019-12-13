package com.revolut.moneytransfer.exception;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/13/2019
 * @see {@link RuntimeException}
 *
 */
public class InvalidAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1692444086942088878L;

	public InvalidAmountException() {

	}

	/**
	 * @param message
	 */
	public InvalidAmountException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public InvalidAmountException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

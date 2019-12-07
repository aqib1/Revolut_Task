package com.revolut.moneytransfer.exception;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 * @see {@link RuntimeException}
 *
 */
public class InvalidResponseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4936149421970526068L;

	public InvalidResponseException() {

	}

	public InvalidResponseException(String message) {
		super(message);
	}

	public InvalidResponseException(String message, Throwable throwable) {
		super(message, throwable);
	}

}

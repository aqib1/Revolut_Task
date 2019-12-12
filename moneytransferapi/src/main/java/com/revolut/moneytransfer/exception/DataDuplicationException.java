package com.revolut.moneytransfer.exception;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 * @see {@link RuntimeException}
 *
 */
public class DataDuplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2809306975311109014L;

	public DataDuplicationException() {

	}

	/**
	 * @param message
	 */
	public DataDuplicationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public DataDuplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}

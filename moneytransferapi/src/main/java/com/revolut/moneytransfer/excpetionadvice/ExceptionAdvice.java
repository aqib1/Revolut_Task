package com.revolut.moneytransfer.excpetionadvice;

import org.eclipse.jetty.http.HttpStatus;
import java.util.Objects;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 */
public class ExceptionAdvice {
	private Exception exception;
	private HttpStatus httpStatus;
	private String message;

	private ExceptionAdvice(Exception exception, HttpStatus httpStatus, String message) {

	}

}

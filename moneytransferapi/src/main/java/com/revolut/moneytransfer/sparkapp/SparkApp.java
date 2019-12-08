package com.revolut.moneytransfer.sparkapp;

import java.util.Objects;

import org.eclipse.jetty.http.HttpStatus;

import com.revolut.moneytransfer.exception.InvalidRequestException;
import com.revolut.moneytransfer.exception.InvalidResponseException;
import com.revolut.moneytransfer.excpetionadvice.ExceptionAdvice;
import com.revolut.moneytransfer.utils.ExceptionUtility;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 *        <p>
 *        This class is used to initialize spark-java application against all
 *        controller and exceptions
 *        </p>
 */
public class SparkApp {
	// we will make object volatile so that in the case of multiple threads,
	// each thread have have updated object value, from main memory
	private static volatile SparkApp sparkApp = null;

	private SparkApp() {

	}

	/**
	 * Registration of exceptions in spark-Application
	 * 
	 * @see {@link InvalidRequestException}
	 * @see {@link InvalidResponseException}
	 */
	public SparkApp setupExceptions() {
		// Creating advice for InvalidRequestException
		ExceptionAdvice.getInstance().exceptionAdvice(ExceptionUtility.builder()
				.withException(InvalidRequestException.class).withStatus(HttpStatus.BAD_REQUEST_400).build());
		// Creating advice for InvalidResponseException
		ExceptionAdvice.getInstance().exceptionAdvice(ExceptionUtility.builder()
				.withException(InvalidResponseException.class).withStatus(HttpStatus.BAD_REQUEST_400).build());
		
		return this;
	}

	// Double check locking singleton pattern
	public static SparkApp getInstance() {
		if (Objects.isNull(sparkApp)) {
			synchronized (SparkApp.class) {
				if (Objects.isNull(sparkApp)) {
					sparkApp = new SparkApp();
				}
			}
		}
		return sparkApp;
	}

}

package com.revolut.moneytransfer.sparkapp;

import java.util.Objects;

import org.eclipse.jetty.http.HttpStatus;

import com.revolut.moneytransfer.exception.InvalidRequestException;
import com.revolut.moneytransfer.excpetionadvice.ExceptionAdvice;
import com.revolut.moneytransfer.models.ExceptionModel;

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

	public SparkApp setupExceptions() {
		ExceptionAdvice.getInstance().exceptionAdvice(ExceptionModel.builder()
				.withException(InvalidRequestException.class).withStatus(HttpStatus.BAD_REQUEST_400).build());

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

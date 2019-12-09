package com.revolut.moneytransfer.sparkapp;

import static spark.Spark.before;
import static spark.Spark.port;

import java.util.Objects;

import org.eclipse.jetty.http.HttpStatus;

import com.revolut.moneytransfer.controller.UserController;
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
	 * <p>
	 * Registration of exceptions in spark-Application
	 * </p>
	 * 
	 * @see {@link InvalidRequestException}
	 * @see {@link InvalidResponseException}
	 * @return {@link SparkApp}
	 */
	public SparkApp registerExceptions() {
		// Creating advice for InvalidRequestException
		ExceptionAdvice.getInstance().exceptionAdvice(ExceptionUtility.builder()
				.withException(InvalidRequestException.class).withStatus(HttpStatus.BAD_REQUEST_400).build());
		// Creating advice for InvalidResponseException
		ExceptionAdvice.getInstance().exceptionAdvice(ExceptionUtility.builder()
				.withException(InvalidResponseException.class).withStatus(HttpStatus.BAD_REQUEST_400).build());

		return this;
	}

	/**
	 * <p>
	 * Registration of controllers in spark-Application
	 * </p>
	 * 
	 * @see {@link UserController}
	 * @return {@link SparkApp}
	 */
	public SparkApp registerControllers() {
		// User controller API registrations
		UserController.getInstance().registerGetAllUserAPI();
		return this;
	}

	/**
	 * <p>
	 * Setting response type passed during the run of Spark application<br>
	 * 
	 * @param responsetType is effective final variable {see java-8 doc}
	 *                      </p>
	 * @param responsetType
	 * @return {@link SparkApp}
	 */
	public SparkApp setResponseType(String responsetType) {
		before((request, response) -> {
			response.type(responsetType);
		});
		return this;
	}

	/**
	 * <p>
	 * Setting port number for Spark application<br>
	 * Default port is 4567
	 * </p>
	 * 
	 * @param port
	 * @return {@link SparkApp}
	 */
	public SparkApp setPort(int port) {
		port(port);
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

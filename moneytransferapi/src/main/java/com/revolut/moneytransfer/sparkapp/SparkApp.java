package com.revolut.moneytransfer.sparkapp;

import static spark.Spark.before;
import static spark.Spark.port;

import java.util.Objects;

import org.eclipse.jetty.http.HttpStatus;

import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.UserController;
import com.revolut.moneytransfer.exception.BadRequestParamsException;
import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.exception.InvalidRequestException;
import com.revolut.moneytransfer.exceptionadvice.ExceptionAdvice;
import com.revolut.moneytransfer.utils.ExceptionUtility;

/**
 * <p>
 * This class is used to initialize spark-java application against all
 * controller and exceptions
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
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
		ExceptionAdvice.getInstance()
				.exceptionAdvice(
						ExceptionUtility.builder().withException(InvalidRequestException.class)
								.withStatus(HttpStatus.BAD_REQUEST_400).build())
				// Creating advice for IllegalArgumentException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(IllegalArgumentException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// Creating advice for NullPointerException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(NullPointerException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// Creating advice for BadRequestException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(BadRequestParamsException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// IllegalMonitorStateException for StampedLock unLockRead method
				.exceptionAdvice(
						ExceptionUtility.builder().withException(IllegalMonitorStateException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// Creating advice for DataNotFoundException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(DataNotFoundException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// Creating advice for DataDuplicationException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(DataDuplicationException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build())
				// Creating advice for InvalidAmountException
				.exceptionAdvice(
						ExceptionUtility.builder().withException(InvalidAmountException.class)
								.withStatus(HttpStatus.EXPECTATION_FAILED_417).build());
		return this;
	}

	/**
	 * <p>
	 * Registration of controllers in spark-Application
	 * </p>
	 * 
	 * @see {@link UserController}
	 * @see {@link AccountController}
	 * @return {@link SparkApp}
	 */
	public SparkApp registerControllers() {
		// User controller API registrations
		UserController.getInstance().registerGetAllUserAPI().registerGetUserByIdAPI()
				.registerPostCreateUserAPI().registerDeleteUserAPI().registerUpdateUserAPI()
				.registerCheckUserAPI();

		// Account controller API registrations
		AccountController.getInstance().registerGetAllAccountAPI().registerGetAccountByIdAPI()
				.registerPostCreateAccountAPI().registerDeleteAccountAPI()
				.registerUpdateAccountAPI().registerCheckAccountAPI().registerWithdrawAPI()
				.registerDepositAPI();
		return this;
	}

	/**
	 * <p>
	 * Setting response type passed during the run of Spark application<br>
	 * responsetType is effective final variable {see java-8 doc}
	 * </p>
	 * 
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

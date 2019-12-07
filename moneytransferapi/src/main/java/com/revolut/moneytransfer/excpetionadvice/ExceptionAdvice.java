package com.revolut.moneytransfer.excpetionadvice;

import static spark.Spark.exception;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.models.ExceptionModel;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 * 
 *        <p>
 *        This class is work like exception advice. We will register our
 *        exceptions and corresponding responses on those exception in
 *        spark-java framework
 *        </p>
 */
public class ExceptionAdvice {
	// we will make object volatile so that in the case of multiple threads,
	// each thread have have updated object value, from main memory
	private volatile static ExceptionAdvice exceptionAdvice = null;

	private ExceptionAdvice() {

	}

	public ExceptionAdvice exceptionAdvice(final ExceptionModel exceptionModel) {
		exception(exceptionModel.getException(), (exception, request, response) -> {
			response.status(exceptionModel.getStatus());
			response.body(new Gson().toJson(null));
		});
		return this;
	}

	// Double check locking singleton pattern
	public static ExceptionAdvice getInstance() {
		if (Objects.isNull(exceptionAdvice)) {
			synchronized (ExceptionAdvice.class) {
				if (Objects.isNull(exceptionAdvice))
					exceptionAdvice = new ExceptionAdvice();
			}
		}

		return exceptionAdvice;
	}

}

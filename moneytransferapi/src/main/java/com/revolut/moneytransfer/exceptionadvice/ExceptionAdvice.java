package com.revolut.moneytransfer.exceptionadvice;

import static spark.Spark.exception;

import java.util.Objects;

import com.google.gson.Gson;
import com.revolut.moneytransfer.dto.responses.ResponseDto;
import com.revolut.moneytransfer.dto.status.StatusType;
import com.revolut.moneytransfer.utils.ExceptionUtility;

/**
 * <p>
 * This class is work like exception advice. We will register our exceptions and
 * corresponding responses on those exception in spark-java framework
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 */
public class ExceptionAdvice {
	// we will make object volatile so that in the case of multiple threads,
	// each thread have have updated object value, from main memory
	private volatile static ExceptionAdvice exceptionAdvice = null;

	private ExceptionAdvice() {

	}

	/**
	 * @param exceptionModel
	 * @return
	 */
	public ExceptionAdvice exceptionAdvice(final ExceptionUtility exceptionModel) {
		exception(exceptionModel.getException(), (exception, request, response) -> {
			response.status(exceptionModel.getStatus());
			response.body(new Gson().toJson(
					ResponseDto.builder().withStatusType(StatusType.ERROR).withMessage(exception.getMessage())));
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

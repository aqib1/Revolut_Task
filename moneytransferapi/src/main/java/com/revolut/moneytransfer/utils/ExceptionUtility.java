package com.revolut.moneytransfer.utils;

import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/7/2019
 */
@Getter
@ToString
public class ExceptionUtility {

	private Class<? extends Exception> exception;
	private int status;

	private ExceptionUtility(Class<? extends Exception> exception, int status) {
		this.exception = exception;
		this.status = status;
	}

	public static Builder builder() {
		return Builder.getInstance();
	}

	/**
	 * Builder pattern for ExceptionModel
	 */
	public static class Builder {
		private Class<? extends Exception> exception;
		private int status;
		// we will make object volatile so that in the case of multiple threads,
		// each thread have have updated object value, from main memory
		private volatile static Builder builder = null;

		private Builder() {

		}

		public Builder withException(Class<? extends Exception> exception) {
			this.exception = exception;
			return this;
		}

		public Builder withStatus(int status) {
			this.status = status;
			return this;
		}

		public ExceptionUtility build() {
			// Primary details [exception, status] cannot be null
			if (Objects.isNull(exception) || Objects.isNull(status)) {
				throw new IllegalArgumentException("IllegalArguments provided for ExceptionModel attribute");
			}
			return new ExceptionUtility(exception, status);
		}

		// Double check locking singleton pattern
		private static Builder getInstance() {
			if (Objects.isNull(builder)) {
				synchronized (Builder.class) {
					if (Objects.isNull(builder))
						builder = new Builder();
				}
			}
			return builder;
		}
	}
}

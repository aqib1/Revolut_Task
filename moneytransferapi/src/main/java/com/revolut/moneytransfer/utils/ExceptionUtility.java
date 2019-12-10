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

	private ExceptionUtility(final Builder builder) {
		this.exception = builder.exception;
		this.status = builder.status;
	}

	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern for ExceptionModel
	 */
	@ToString
	public static class Builder {
		private Class<? extends Exception> exception;
		private int status;
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
			return new ExceptionUtility(this);
		}
	}
}

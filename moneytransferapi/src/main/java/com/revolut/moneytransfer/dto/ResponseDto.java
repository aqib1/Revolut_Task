package com.revolut.moneytransfer.dto;

import com.google.gson.JsonElement;
import com.revolut.moneytransfer.dto.status.StatusType;

import lombok.Getter;
import lombok.ToString;

/**
 * <p>
 * Generic response data transfer object
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 */
@Getter
@ToString
public class ResponseDto {

	private StatusType statusType;
	private JsonElement data;
	private String message;

	/**
	 * @param builder
	 */
	private ResponseDto(final Builder builder) {
		this.statusType = builder.statusType;
		this.data = builder.data;
		this.message = builder.message;
	}

	/**
	 * @return
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern for Response DTO class
	 * 
	 */
	@ToString
	public static class Builder {
		private StatusType statusType;
		private JsonElement data;
		private String message;

		private Builder() {

		}

		/**
		 * @param statusType
		 * @return
		 */
		public Builder withStatusType(StatusType statusType) {
			this.statusType = statusType;
			return this;
		}

		/**
		 * @param data
		 * @return
		 */
		public Builder withData(JsonElement data) {
			this.data = data;
			return this;
		}

		/**
		 * @param message
		 * @return
		 */
		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		/**
		 * @param format
		 * @param args
		 * @return
		 */
		public Builder withMessage(String format, Object... args) {
			this.message = String.format(format, args);
			return this;
		}

		/**
		 * @return
		 */
		public ResponseDto build() {
			return new ResponseDto(this);
		}

	}
}

package com.revolut.moneytransfer.dto;

import com.revolut.moneytransfer.dto.status.StatusType;

import lombok.Getter;
import lombok.ToString;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 *        <p>
 *        Generic response data transfer object
 *        </p>
 */
@Getter
@ToString
public class ResponseDto {

	private StatusType statusType;

	private ResponseDto(final Builder builder) {
		this.statusType = builder.statusType;
	}

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
		private Builder() {

		}

		public Builder withStatusType(StatusType statusType) {
			this.statusType = statusType;
			return this;
		}

		public ResponseDto build() {
			return new ResponseDto(this);
		}

	}
}

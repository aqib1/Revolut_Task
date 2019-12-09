package com.revolut.moneytransfer.dto;

import java.util.Objects;

import com.revolut.moneytransfer.dto.status.StatusType;

import lombok.Getter;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 *        <p>
 *        Generic response data transfer object
 *        </p>
 */
@Getter
public class ResponseDto {

	private StatusType statusType;

	private ResponseDto(StatusType statusType) {
		this.statusType = statusType;
	}

	public static Builder builder() {
		return Builder.getInstance();
	}

	/**
	 * Builder pattern for Response DTO class
	 * 
	 */
	public static class Builder {
		private StatusType statusType;
		// we will make object volatile so that in the case of multiple threads,
		// each thread have have updated object value, from main memory
		private volatile static Builder builder = null;

		private Builder() {

		}

		public Builder withStatusType(StatusType statusType) {
			this.statusType = statusType;
			return this;
		}

		public ResponseDto build() {
			return new ResponseDto(this.statusType);
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

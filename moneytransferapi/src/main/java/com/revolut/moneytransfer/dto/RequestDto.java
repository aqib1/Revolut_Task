package com.revolut.moneytransfer.dto;

import com.revolut.moneytransfer.models.UserModel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestDto {
	private UserModel user;

	/**
	 * @param builder
	 */
	private RequestDto(Builder builder) {
		this.user = builder.user;
	}

	/**
	 * @return
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder pattern for Request DTO class
	 * 
	 */
	public static class Builder {
		private UserModel user;

		public Builder withUser(UserModel user) {
			this.user = user;
			return this;
		}

		public RequestDto build() {
			return new RequestDto(this);
		}
	}
}

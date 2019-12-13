package com.revolut.moneytransfer.dto.requests;

import com.revolut.moneytransfer.models.UserModel;

import lombok.Getter;
import lombok.ToString;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/9/2019
 */
@Getter
@ToString
public class UserRequestDto {
	private UserModel user;

	/**
	 * @param builder
	 */
	private UserRequestDto(Builder builder) {
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

		public UserRequestDto build() {
			return new UserRequestDto(this);
		}
	}
}

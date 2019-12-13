package com.revolut.moneytransfer.dto.requests;

import com.revolut.moneytransfer.models.AccountModel;

import lombok.Getter;
import lombok.ToString;

/**
 * @author AQIB JAVED
 * @since 12/13/2019
 * @version 1.0
 */
@Getter
@ToString
public class AccountRequestDto {
	private AccountModel account;

	/**
	 * @param builder
	 */
	private AccountRequestDto(Builder builder) {
		this.account = builder.account;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private AccountModel account;

		private Builder() {

		}

		/**
		 * @param account
		 * @return
		 */
		public Builder withAccount(AccountModel account) {
			this.account = account;
			return this;
		}

		public AccountRequestDto build() {
			return new AccountRequestDto(this);
		}

	}
}

package com.revolut.moneytransfer.dto;

import com.revolut.moneytransfer.models.AccountModel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountRequestDto {
	private AccountModel account;

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

		public Builder withAccount(AccountModel account) {
			this.account = account;
			return this;
		}

		public AccountRequestDto build() {
			return new AccountRequestDto(this);
		}

	}
}

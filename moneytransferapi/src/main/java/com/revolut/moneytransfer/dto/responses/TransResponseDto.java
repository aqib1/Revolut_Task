package com.revolut.moneytransfer.dto.responses;

import com.revolut.moneytransfer.models.AccountModel;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransResponseDto {
	private AccountModel reciever;
	private AccountModel sender;

	private TransResponseDto(Builder build) {
		this.reciever = build.reciever;
		this.sender = build.sender;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private AccountModel reciever;
		private AccountModel sender;

		public Builder withReciever(AccountModel reciever) {
			this.reciever = reciever;
			return this;
		}

		public Builder withSender(AccountModel account) {
			this.sender = account;
			return this;
		}

		public TransResponseDto build() {
			return new TransResponseDto(this);
		}
	}
}

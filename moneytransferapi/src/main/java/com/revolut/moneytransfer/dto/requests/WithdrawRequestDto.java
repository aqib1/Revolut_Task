package com.revolut.moneytransfer.dto.requests;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WithdrawRequestDto {
	private String accountId;
	private volatile BigDecimal amount;
	
	
	private WithdrawRequestDto(Builder builder) {
		this.accountId = builder.accountId;
		this.amount = builder.amount;
	}
	
	public static Builder build() {
		return new Builder();
	}

	public static class Builder {
		private String accountId;
		private volatile BigDecimal amount;

		private Builder() {

		}

		public Builder withAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public Builder withAccountId(String accountId) {
			this.accountId = accountId;
			return this;
		}

		public WithdrawRequestDto builder() {
			return new WithdrawRequestDto(this);
		}
	}
}

package com.revolut.moneytransfer.dto.requests;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest {
	private String accountId;
	private volatile BigDecimal amount;

	private DepositRequest(Builder builder) {
		this.accountId = builder.accountId;
		this.amount = builder.amount;
	}

	public static Builder builder() {
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

		public DepositRequest build() {
			return new DepositRequest(this);
		}
	}

}

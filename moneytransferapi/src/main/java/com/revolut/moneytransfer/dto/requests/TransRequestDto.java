package com.revolut.moneytransfer.dto.requests;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TransRequestDto {
	private String fromAccount;
	private String toAccount;
	private volatile BigDecimal amount;

	private TransRequestDto(Builder builder) {
		this.fromAccount = builder.fromAccount;
		this.toAccount = builder.toAccount;
		this.amount = builder.amount;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private String fromAccount;
		private String toAccount;
		private volatile BigDecimal amount;

		private Builder() {

		}

		/**
		 * @param fromAccount
		 * @return
		 */
		public Builder withFromAccount(String fromAccount) {
			this.fromAccount = fromAccount;
			return this;
		}

		/**
		 * @param toAccount
		 * @return
		 */
		public Builder withToAccount(String toAccount) {
			this.toAccount = toAccount;
			return this;
		}

		/**
		 * @param amount
		 * @return
		 */
		public Builder withAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public TransRequestDto build() {
			return new TransRequestDto(this);
		}
	}
}

package com.revolut.moneytransfer.dto.responses;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DepositResponse {
	private BigDecimal depositAmount;
	private BigDecimal currentAmount;

	private DepositResponse(Builder build) {
		this.depositAmount = build.depositAmount;
		this.currentAmount = build.currentAmount;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private BigDecimal depositAmount;
		private BigDecimal currentAmount;

		private Builder() {

		}

		public Builder withDepositAmount(BigDecimal depositAmount) {
			this.depositAmount = depositAmount;
			return this;
		}

		public Builder withCurrentAmount(BigDecimal currentAmount) {
			this.currentAmount = currentAmount;
			return this;
		}

		public DepositResponse build() {
			return new DepositResponse(this);
		}
	}

}

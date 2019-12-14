package com.revolut.moneytransfer.dto.responses;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WithdrawResponseDto {
	private BigDecimal withDrawAmount;
	private BigDecimal currentAmount;

	private WithdrawResponseDto(Builder builder) {
		this.withDrawAmount = builder.withDrawAmount;
		this.currentAmount = builder.currentAmount;

	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private BigDecimal withDrawAmount;
		private BigDecimal currentAmount;

		private Builder() {

		}

		public Builder withDrawAmount(BigDecimal withDrawAmount) {
			this.withDrawAmount = withDrawAmount;
			return this;
		}

		public Builder currentAmount(BigDecimal currentAmount) {
			this.currentAmount = currentAmount;
			return this;
		}

		public WithdrawResponseDto build() {
			return new WithdrawResponseDto(this);
		}
	}
}

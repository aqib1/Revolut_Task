package com.revolut.moneytransfer.dto.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class WithdrawResponseDto {
	private BigDecimal withDrawAmount;
	private BigDecimal currentAmount;

	public WithdrawResponseDto withDrawAmount(BigDecimal withDrawAmount) {
		this.withDrawAmount = withDrawAmount;
		return this;
	}
	
	public WithdrawResponseDto currentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
		return this;
	}
}

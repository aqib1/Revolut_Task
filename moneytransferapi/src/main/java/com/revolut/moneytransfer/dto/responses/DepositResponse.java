package com.revolut.moneytransfer.dto.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositResponse {
	private BigDecimal depositAmount;
	private BigDecimal currentAmount;

	public DepositResponse depositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
		return this;
	}
	
	public DepositResponse currentAmount(BigDecimal currentAmount) {
		this.currentAmount = currentAmount;
		return this;
	}
}

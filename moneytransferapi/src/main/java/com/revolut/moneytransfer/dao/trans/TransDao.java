package com.revolut.moneytransfer.dao.trans;

import com.revolut.moneytransfer.dto.requests.TransRequestDto;
import com.revolut.moneytransfer.dto.responses.TransResponseDto;
import com.revolut.moneytransfer.exception.InvalidAmountException;

public interface TransDao {
	TransResponseDto transfer(TransRequestDto request) throws InvalidAmountException;
}

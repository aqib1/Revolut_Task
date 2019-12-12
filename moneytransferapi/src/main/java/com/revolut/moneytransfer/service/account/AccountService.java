package com.revolut.moneytransfer.service.account;

import com.revolut.moneytransfer.dto.AccountRequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;

/**
 * <p>
 * Account-service skeleton
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */
public interface AccountService {

	// Get-all account from DB
	ResponseDto getAll();

	// Get account by id from DB
	ResponseDto getById(String id) throws IllegalArgumentException;

	// Create new account and returned newly created user
	ResponseDto create(AccountRequestDto request);

	// Update account and return updated user
	ResponseDto update(AccountRequestDto request);

	// Check is account exists by id
	ResponseDto exists(String id);

	// Delete account by id
	ResponseDto delete(String id);

}

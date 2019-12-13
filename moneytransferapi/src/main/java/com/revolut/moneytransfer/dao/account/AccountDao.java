package com.revolut.moneytransfer.dao.account;

import java.util.Collection;

import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.models.AccountModel;

/**
 * <p>
 * User-DAO skeleton
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */
public interface AccountDao {
	// Get-all account from DB
	Collection<AccountModel> getAll();

	// Get account by id from DB
	AccountModel getById(String id);

	// Create new account and returned newly created account
	AccountModel create(AccountModel account);

	// Update account and return updated account
	AccountModel update(AccountModel account);

	// Check is account exists by id
	boolean exists(String id);

	// Delete account by id
	AccountModel delete(String id);
	
	// Withdraw amount from account
	WithdrawResponseDto withDraw(WithdrawRequestDto withdrawRequestDto);

	// Deposit amount from account
	DepositResponse deposit(DepositRequest depositRequest);
}

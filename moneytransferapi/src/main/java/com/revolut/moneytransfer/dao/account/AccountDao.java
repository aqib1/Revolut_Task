package com.revolut.moneytransfer.dao.account;

import java.util.Collection;

import com.revolut.moneytransfer.dto.requests.DepositRequest;
import com.revolut.moneytransfer.dto.requests.WithdrawRequestDto;
import com.revolut.moneytransfer.dto.responses.DepositResponse;
import com.revolut.moneytransfer.dto.responses.WithdrawResponseDto;
import com.revolut.moneytransfer.exception.DataDuplicationException;
import com.revolut.moneytransfer.exception.DataNotFoundException;
import com.revolut.moneytransfer.exception.InvalidAmountException;
import com.revolut.moneytransfer.models.AccountModel;

/**
 * <p>
 * Account-DAO skeleton
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
	AccountModel create(AccountModel account)
			throws DataDuplicationException, IllegalArgumentException;

	// Update account and return updated account
	AccountModel update(AccountModel account) throws DataNotFoundException;

	// Check is account exists by id
	boolean exists(String id) throws DataNotFoundException;

	// Delete account by id
	AccountModel delete(String id) throws DataNotFoundException;

	// Withdraw amount from account
	WithdrawResponseDto withDraw(WithdrawRequestDto withdrawRequestDto)
			throws DataNotFoundException, InvalidAmountException;

	// Deposit amount from account
	DepositResponse deposit(DepositRequest depositRequest)
			throws DataNotFoundException, InvalidAmountException;
}

package com.revolut.moneytransfer.service.user.Impl;

import javax.inject.Inject;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.dto.AccountRequestDto;
import com.revolut.moneytransfer.dto.ResponseDto;
import com.revolut.moneytransfer.service.AccountService;

/**
 * <p>
 * Account service implementation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 * 
 */
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	/**
	 * <p>
	 * Account DAO injection using dagger2
	 * </p>
	 * 
	 * @param userDao
	 */
	@Inject
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public ResponseDto getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto getById(String id) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto create(AccountRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto update(AccountRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto exists(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseDto delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

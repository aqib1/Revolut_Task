package com.revolut.moneytransfer.dao.account.Impl;

import java.util.Collection;
import java.util.Objects;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.models.AccountModel;

public class AccountDaoImpl implements AccountDao {
	private static AccountDaoImpl accountdaoImpl = null;

	private AccountDaoImpl() {

	}

	@Override
	public Collection<AccountModel> getAll() {
		return null;
	}

	@Override
	public AccountModel getById(String id) {
		return null;
	}

	@Override
	public AccountModel create(AccountModel account) {
		return null;
	}

	@Override
	public AccountModel update(AccountModel account) {
		return null;
	}

	@Override
	public boolean exists(String id) {
		return false;
	}

	@Override
	public AccountModel delete(String id) {
		return null;
	}

	public static AccountDaoImpl getInstance() {
		if (Objects.isNull(accountdaoImpl))
			synchronized (AccountDaoImpl.class) {
				if (Objects.isNull(accountdaoImpl))
					accountdaoImpl = new AccountDaoImpl();
			}
		return accountdaoImpl;
	}
}

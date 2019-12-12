package com.revolut.moneytransfer.dagger2.modules;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dao.account.AccountDao;
import com.revolut.moneytransfer.dao.account.Impl.AccountDaoImpl;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.user.Impl.AccountServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <p>
 * Dagger2 module created for account service. This module describing two
 * providers, one is for account DAO and other is for account service
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */

@Module
public class AccountServiceModule {

	/**
	 * @return {@link AccountDaoImpl}
	 */
	@Provides
	public AccountDao getAccountDao() {
		return AccountDaoImpl.getInstance();
	}

	/**
	 * @return {@link AccountServiceImpl}
	 */
	@Provides
	@Singleton
	public AccountService getAccountService() {
		return new AccountServiceImpl(getAccountDao());
	}
}

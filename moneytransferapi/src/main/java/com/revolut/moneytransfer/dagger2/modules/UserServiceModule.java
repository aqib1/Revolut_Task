package com.revolut.moneytransfer.dagger2.modules;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.dao.user.Impl.UserDaoImpl;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.service.user.Impl.UserServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <p>
 * Dagger2 module created for user service. This module describing two
 * providers, one is for user DAO and other is for user service
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 */
@Module
public class UserServiceModule {
	
	/**
	 * @return {@link UserDaoImpl}
	 */
	@Provides
	public UserDao getUserDao() {
		return UserDaoImpl.getInstance();
	}

	/**
	 * @return {@link UserServiceImpl}
	 */
	@Provides
	@Singleton
	public UserService getUserService() {
		return new UserServiceImpl(getUserDao());
	}
}

package com.revolut.moneytransfer.dagger2.modules;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dao.user.UserDao;
import com.revolut.moneytransfer.dao.user.Impl.UserDaoImpl;
import com.revolut.moneytransfer.service.user.UserService;
import com.revolut.moneytransfer.service.user.Impl.UserServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class UserServiceModule {

	@Provides
	public UserDao getUserDao() {
		return UserDaoImpl.getInstance();
	}

	@Provides
	@Singleton
	public UserService getUserService() {
		return new UserServiceImpl(getUserDao());
	}
}

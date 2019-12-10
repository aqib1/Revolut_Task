package com.revolut.moneytransfer.dagger2.components;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dagger2.modules.UserServiceModule;
import com.revolut.moneytransfer.service.user.Impl.UserServiceImpl;

import dagger.Component;

/**
 * <p>
 * Component module of dagger2 for User service. Component should be singleton
 * if provider is given by singleton annotation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/10/2019
 */
@Singleton
@Component(modules = UserServiceModule.class)
public interface UserServiceComponent {
	/**
	 * @return {@link UserServiceImpl}
	 */
	UserServiceImpl buildUserServiceImpl();
}

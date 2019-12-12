package com.revolut.moneytransfer.dagger2.components;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dagger2.modules.AccountServiceModule;
import com.revolut.moneytransfer.service.user.Impl.AccountServiceImpl;

import dagger.Component;

/**
 * <p>
 * Component module of dagger2 for account service. Component should be singleton
 * if provider is given by singleton annotation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/12/2019
 */
@Singleton
@Component(modules = AccountServiceModule.class)
public interface AccountServiceComponent {

	/**
	 * @return {@link AccountServiceImpl}
	 */
	AccountServiceImpl buildAccountServiceImpl();
}

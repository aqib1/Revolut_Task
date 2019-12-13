package com.revolut.moneytransfer.dagger2.components;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dagger2.modules.TransServiceModule;
import com.revolut.moneytransfer.service.trans.Impl.TransServiceImpl;

import dagger.Component;

/**
 * <p>
 * Component module of dagger2 for transaction service. Component should be
 * singleton if provider is given by singleton annotation
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/13/2019
 */
@Singleton
@Component(modules = TransServiceModule.class)
public interface TransServiceComponent {
	/**
	 * @return {@link TransServiceImpl}
	 */
	TransServiceImpl buildTransServiceImpl();
}

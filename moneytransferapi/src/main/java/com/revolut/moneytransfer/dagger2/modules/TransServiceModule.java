package com.revolut.moneytransfer.dagger2.modules;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dao.trans.TransDao;
import com.revolut.moneytransfer.dao.trans.Impl.TransDaoImpl;
import com.revolut.moneytransfer.service.trans.Impl.TransServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <p>
 * Dagger2 module created for transaction service. This module describing two
 * providers, one is for transaction DAO and other is for transaction service
 * </p>
 * 
 * @author AQIB JAVED
 * @version 1.0
 * @since 12/13/2019
 */

@Module
public class TransServiceModule {

	/**
	 * @return {@link TransDao}
	 */
	@Provides
	public TransDao getTransDao() {
		return TransDaoImpl.getInstance();
	}

	/**
	 * @return {@link TransServiceImpl}
	 */
	@Singleton
	@Provides
	public TransServiceImpl getTransServiceImpl() {
		return new TransServiceImpl(getTransDao());
	}
}

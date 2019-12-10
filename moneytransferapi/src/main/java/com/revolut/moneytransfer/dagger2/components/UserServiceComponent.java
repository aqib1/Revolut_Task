package com.revolut.moneytransfer.dagger2.components;

import javax.inject.Singleton;

import com.revolut.moneytransfer.dagger2.modules.UserServiceModule;
import com.revolut.moneytransfer.service.user.Impl.UserServiceImpl;

import dagger.Component;

@Singleton
@Component(modules = UserServiceModule.class)
public interface UserServiceComponent {
	UserServiceImpl buildUserServiceImpl();
}

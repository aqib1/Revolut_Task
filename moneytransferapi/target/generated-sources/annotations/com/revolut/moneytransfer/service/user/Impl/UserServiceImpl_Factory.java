package com.revolut.moneytransfer.service.user.Impl;

import com.revolut.moneytransfer.dao.user.UserDao;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserServiceImpl_Factory implements Factory<UserServiceImpl> {
  private final Provider<UserDao> userDaoProvider;

  public UserServiceImpl_Factory(Provider<UserDao> userDaoProvider) {
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public UserServiceImpl get() {
    return provideInstance(userDaoProvider);
  }

  public static UserServiceImpl provideInstance(Provider<UserDao> userDaoProvider) {
    return new UserServiceImpl(userDaoProvider.get());
  }

  public static UserServiceImpl_Factory create(Provider<UserDao> userDaoProvider) {
    return new UserServiceImpl_Factory(userDaoProvider);
  }

  public static UserServiceImpl newUserServiceImpl(UserDao userDao) {
    return new UserServiceImpl(userDao);
  }
}

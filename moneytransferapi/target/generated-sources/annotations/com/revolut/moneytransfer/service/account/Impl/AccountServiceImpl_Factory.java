package com.revolut.moneytransfer.service.account.Impl;

import com.revolut.moneytransfer.dao.account.AccountDao;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AccountServiceImpl_Factory implements Factory<AccountServiceImpl> {
  private final Provider<AccountDao> accountDaoProvider;

  public AccountServiceImpl_Factory(Provider<AccountDao> accountDaoProvider) {
    this.accountDaoProvider = accountDaoProvider;
  }

  @Override
  public AccountServiceImpl get() {
    return provideInstance(accountDaoProvider);
  }

  public static AccountServiceImpl provideInstance(Provider<AccountDao> accountDaoProvider) {
    return new AccountServiceImpl(accountDaoProvider.get());
  }

  public static AccountServiceImpl_Factory create(Provider<AccountDao> accountDaoProvider) {
    return new AccountServiceImpl_Factory(accountDaoProvider);
  }

  public static AccountServiceImpl newAccountServiceImpl(AccountDao accountDao) {
    return new AccountServiceImpl(accountDao);
  }
}

package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.dao.account.AccountDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AccountServiceModule_GetAccountDaoFactory implements Factory<AccountDao> {
  private final AccountServiceModule module;

  public AccountServiceModule_GetAccountDaoFactory(AccountServiceModule module) {
    this.module = module;
  }

  @Override
  public AccountDao get() {
    return provideInstance(module);
  }

  public static AccountDao provideInstance(AccountServiceModule module) {
    return proxyGetAccountDao(module);
  }

  public static AccountServiceModule_GetAccountDaoFactory create(AccountServiceModule module) {
    return new AccountServiceModule_GetAccountDaoFactory(module);
  }

  public static AccountDao proxyGetAccountDao(AccountServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getAccountDao(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

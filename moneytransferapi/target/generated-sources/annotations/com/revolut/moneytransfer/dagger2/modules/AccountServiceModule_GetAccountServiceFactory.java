package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.service.account.AccountService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AccountServiceModule_GetAccountServiceFactory
    implements Factory<AccountService> {
  private final AccountServiceModule module;

  public AccountServiceModule_GetAccountServiceFactory(AccountServiceModule module) {
    this.module = module;
  }

  @Override
  public AccountService get() {
    return provideInstance(module);
  }

  public static AccountService provideInstance(AccountServiceModule module) {
    return proxyGetAccountService(module);
  }

  public static AccountServiceModule_GetAccountServiceFactory create(AccountServiceModule module) {
    return new AccountServiceModule_GetAccountServiceFactory(module);
  }

  public static AccountService proxyGetAccountService(AccountServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getAccountService(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

package com.revolut.moneytransfer.dagger2.components;

import com.revolut.moneytransfer.dagger2.modules.AccountServiceModule;
import com.revolut.moneytransfer.dagger2.modules.AccountServiceModule_GetAccountDaoFactory;
import com.revolut.moneytransfer.service.account.Impl.AccountServiceImpl;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAccountServiceComponent implements AccountServiceComponent {
  private AccountServiceModule accountServiceModule;

  private DaggerAccountServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AccountServiceComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.accountServiceModule = builder.accountServiceModule;
  }

  @Override
  public AccountServiceImpl buildAccountServiceImpl() {
    return new AccountServiceImpl(
        AccountServiceModule_GetAccountDaoFactory.proxyGetAccountDao(accountServiceModule));
  }

  public static final class Builder {
    private AccountServiceModule accountServiceModule;

    private Builder() {}

    public AccountServiceComponent build() {
      if (accountServiceModule == null) {
        this.accountServiceModule = new AccountServiceModule();
      }
      return new DaggerAccountServiceComponent(this);
    }

    public Builder accountServiceModule(AccountServiceModule accountServiceModule) {
      this.accountServiceModule = Preconditions.checkNotNull(accountServiceModule);
      return this;
    }
  }
}

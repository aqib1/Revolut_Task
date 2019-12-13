package com.revolut.moneytransfer.dagger2.components;

import com.revolut.moneytransfer.dagger2.modules.UserServiceModule;
import com.revolut.moneytransfer.dagger2.modules.UserServiceModule_GetUserDaoFactory;
import com.revolut.moneytransfer.service.user.Impl.UserServiceImpl;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerUserServiceComponent implements UserServiceComponent {
  private UserServiceModule userServiceModule;

  private DaggerUserServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static UserServiceComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.userServiceModule = builder.userServiceModule;
  }

  @Override
  public UserServiceImpl buildUserServiceImpl() {
    return new UserServiceImpl(
        UserServiceModule_GetUserDaoFactory.proxyGetUserDao(userServiceModule));
  }

  public static final class Builder {
    private UserServiceModule userServiceModule;

    private Builder() {}

    public UserServiceComponent build() {
      if (userServiceModule == null) {
        this.userServiceModule = new UserServiceModule();
      }
      return new DaggerUserServiceComponent(this);
    }

    public Builder userServiceModule(UserServiceModule userServiceModule) {
      this.userServiceModule = Preconditions.checkNotNull(userServiceModule);
      return this;
    }
  }
}

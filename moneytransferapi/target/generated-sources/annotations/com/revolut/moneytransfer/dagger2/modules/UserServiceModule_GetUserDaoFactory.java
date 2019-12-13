package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.dao.user.UserDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserServiceModule_GetUserDaoFactory implements Factory<UserDao> {
  private final UserServiceModule module;

  public UserServiceModule_GetUserDaoFactory(UserServiceModule module) {
    this.module = module;
  }

  @Override
  public UserDao get() {
    return provideInstance(module);
  }

  public static UserDao provideInstance(UserServiceModule module) {
    return proxyGetUserDao(module);
  }

  public static UserServiceModule_GetUserDaoFactory create(UserServiceModule module) {
    return new UserServiceModule_GetUserDaoFactory(module);
  }

  public static UserDao proxyGetUserDao(UserServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getUserDao(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

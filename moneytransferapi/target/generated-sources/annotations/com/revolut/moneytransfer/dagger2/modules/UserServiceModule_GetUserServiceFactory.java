package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.service.user.UserService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UserServiceModule_GetUserServiceFactory implements Factory<UserService> {
  private final UserServiceModule module;

  public UserServiceModule_GetUserServiceFactory(UserServiceModule module) {
    this.module = module;
  }

  @Override
  public UserService get() {
    return provideInstance(module);
  }

  public static UserService provideInstance(UserServiceModule module) {
    return proxyGetUserService(module);
  }

  public static UserServiceModule_GetUserServiceFactory create(UserServiceModule module) {
    return new UserServiceModule_GetUserServiceFactory(module);
  }

  public static UserService proxyGetUserService(UserServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getUserService(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

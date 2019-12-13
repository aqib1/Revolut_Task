package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.dao.trans.TransDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TransServiceModule_GetTransDaoFactory implements Factory<TransDao> {
  private final TransServiceModule module;

  public TransServiceModule_GetTransDaoFactory(TransServiceModule module) {
    this.module = module;
  }

  @Override
  public TransDao get() {
    return provideInstance(module);
  }

  public static TransDao provideInstance(TransServiceModule module) {
    return proxyGetTransDao(module);
  }

  public static TransServiceModule_GetTransDaoFactory create(TransServiceModule module) {
    return new TransServiceModule_GetTransDaoFactory(module);
  }

  public static TransDao proxyGetTransDao(TransServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getTransDao(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

package com.revolut.moneytransfer.dagger2.modules;

import com.revolut.moneytransfer.service.trans.Impl.TransServiceImpl;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TransServiceModule_GetTransServiceImplFactory
    implements Factory<TransServiceImpl> {
  private final TransServiceModule module;

  public TransServiceModule_GetTransServiceImplFactory(TransServiceModule module) {
    this.module = module;
  }

  @Override
  public TransServiceImpl get() {
    return provideInstance(module);
  }

  public static TransServiceImpl provideInstance(TransServiceModule module) {
    return proxyGetTransServiceImpl(module);
  }

  public static TransServiceModule_GetTransServiceImplFactory create(TransServiceModule module) {
    return new TransServiceModule_GetTransServiceImplFactory(module);
  }

  public static TransServiceImpl proxyGetTransServiceImpl(TransServiceModule instance) {
    return Preconditions.checkNotNull(
        instance.getTransServiceImpl(), "Cannot return null from a non-@Nullable @Provides method");
  }
}

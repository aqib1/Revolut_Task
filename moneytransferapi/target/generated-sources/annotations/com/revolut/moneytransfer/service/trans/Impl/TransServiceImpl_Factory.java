package com.revolut.moneytransfer.service.trans.Impl;

import com.revolut.moneytransfer.dao.trans.TransDao;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class TransServiceImpl_Factory implements Factory<TransServiceImpl> {
  private final Provider<TransDao> transDaoProvider;

  public TransServiceImpl_Factory(Provider<TransDao> transDaoProvider) {
    this.transDaoProvider = transDaoProvider;
  }

  @Override
  public TransServiceImpl get() {
    return provideInstance(transDaoProvider);
  }

  public static TransServiceImpl provideInstance(Provider<TransDao> transDaoProvider) {
    return new TransServiceImpl(transDaoProvider.get());
  }

  public static TransServiceImpl_Factory create(Provider<TransDao> transDaoProvider) {
    return new TransServiceImpl_Factory(transDaoProvider);
  }

  public static TransServiceImpl newTransServiceImpl(TransDao transDao) {
    return new TransServiceImpl(transDao);
  }
}

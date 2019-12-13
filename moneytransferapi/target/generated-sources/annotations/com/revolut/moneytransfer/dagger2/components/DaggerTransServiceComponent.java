package com.revolut.moneytransfer.dagger2.components;

import com.revolut.moneytransfer.dagger2.modules.TransServiceModule;
import com.revolut.moneytransfer.dagger2.modules.TransServiceModule_GetTransServiceImplFactory;
import com.revolut.moneytransfer.service.trans.Impl.TransServiceImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerTransServiceComponent implements TransServiceComponent {
  private Provider<TransServiceImpl> getTransServiceImplProvider;

  private DaggerTransServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static TransServiceComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.getTransServiceImplProvider =
        DoubleCheck.provider(
            TransServiceModule_GetTransServiceImplFactory.create(builder.transServiceModule));
  }

  @Override
  public TransServiceImpl buildTransServiceImpl() {
    return getTransServiceImplProvider.get();
  }

  public static final class Builder {
    private TransServiceModule transServiceModule;

    private Builder() {}

    public TransServiceComponent build() {
      if (transServiceModule == null) {
        this.transServiceModule = new TransServiceModule();
      }
      return new DaggerTransServiceComponent(this);
    }

    public Builder transServiceModule(TransServiceModule transServiceModule) {
      this.transServiceModule = Preconditions.checkNotNull(transServiceModule);
      return this;
    }
  }
}

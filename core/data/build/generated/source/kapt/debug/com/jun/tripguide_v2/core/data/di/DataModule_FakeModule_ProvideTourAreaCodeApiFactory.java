package com.jun.tripguide_v2.core.data.di;

import android.content.Context;
import com.jun.tripguide_v2.core.data.api.fake.AssetsTourAreaCodeApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DataModule_FakeModule_ProvideTourAreaCodeApiFactory implements Factory<AssetsTourAreaCodeApi> {
  private final Provider<Context> contextProvider;

  public DataModule_FakeModule_ProvideTourAreaCodeApiFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AssetsTourAreaCodeApi get() {
    return provideTourAreaCodeApi(contextProvider.get());
  }

  public static DataModule_FakeModule_ProvideTourAreaCodeApiFactory create(
      Provider<Context> contextProvider) {
    return new DataModule_FakeModule_ProvideTourAreaCodeApiFactory(contextProvider);
  }

  public static AssetsTourAreaCodeApi provideTourAreaCodeApi(Context context) {
    return Preconditions.checkNotNullFromProvides(DataModule.FakeModule.INSTANCE.provideTourAreaCodeApi(context));
  }
}

package com.jun.tripguide_v2.core.data.di;

import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi;
import com.jun.tripguide_v2.core.data.repository.AreaCodeRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class DataModule_FakeModule_ProvideAreaCodeRepositoryFactory implements Factory<AreaCodeRepository> {
  private final Provider<TourAreaCodeApi> tourAreaCodeApiProvider;

  public DataModule_FakeModule_ProvideAreaCodeRepositoryFactory(
      Provider<TourAreaCodeApi> tourAreaCodeApiProvider) {
    this.tourAreaCodeApiProvider = tourAreaCodeApiProvider;
  }

  @Override
  public AreaCodeRepository get() {
    return provideAreaCodeRepository(tourAreaCodeApiProvider.get());
  }

  public static DataModule_FakeModule_ProvideAreaCodeRepositoryFactory create(
      Provider<TourAreaCodeApi> tourAreaCodeApiProvider) {
    return new DataModule_FakeModule_ProvideAreaCodeRepositoryFactory(tourAreaCodeApiProvider);
  }

  public static AreaCodeRepository provideAreaCodeRepository(TourAreaCodeApi tourAreaCodeApi) {
    return Preconditions.checkNotNullFromProvides(DataModule.FakeModule.INSTANCE.provideAreaCodeRepository(tourAreaCodeApi));
  }
}

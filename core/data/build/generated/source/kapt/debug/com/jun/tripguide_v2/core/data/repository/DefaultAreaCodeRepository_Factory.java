package com.jun.tripguide_v2.core.data.repository;

import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DefaultAreaCodeRepository_Factory implements Factory<DefaultAreaCodeRepository> {
  private final Provider<TourAreaCodeApi> tourAreaCodeApiProvider;

  public DefaultAreaCodeRepository_Factory(Provider<TourAreaCodeApi> tourAreaCodeApiProvider) {
    this.tourAreaCodeApiProvider = tourAreaCodeApiProvider;
  }

  @Override
  public DefaultAreaCodeRepository get() {
    return newInstance(tourAreaCodeApiProvider.get());
  }

  public static DefaultAreaCodeRepository_Factory create(
      Provider<TourAreaCodeApi> tourAreaCodeApiProvider) {
    return new DefaultAreaCodeRepository_Factory(tourAreaCodeApiProvider);
  }

  public static DefaultAreaCodeRepository newInstance(TourAreaCodeApi tourAreaCodeApi) {
    return new DefaultAreaCodeRepository(tourAreaCodeApi);
  }
}

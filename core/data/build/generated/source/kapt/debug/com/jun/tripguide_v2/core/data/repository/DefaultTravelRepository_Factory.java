package com.jun.tripguide_v2.core.data.repository;

import com.jun.tripguide_v2_core.database.TravelDao;
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
public final class DefaultTravelRepository_Factory implements Factory<DefaultTravelRepository> {
  private final Provider<TravelDao> travelDaoProvider;

  public DefaultTravelRepository_Factory(Provider<TravelDao> travelDaoProvider) {
    this.travelDaoProvider = travelDaoProvider;
  }

  @Override
  public DefaultTravelRepository get() {
    return newInstance(travelDaoProvider.get());
  }

  public static DefaultTravelRepository_Factory create(Provider<TravelDao> travelDaoProvider) {
    return new DefaultTravelRepository_Factory(travelDaoProvider);
  }

  public static DefaultTravelRepository newInstance(TravelDao travelDao) {
    return new DefaultTravelRepository(travelDao);
  }
}

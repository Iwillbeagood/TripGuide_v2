package com.jun.tripguide_v2.core.domain.usecase;

import com.jun.tripguide_v2.core.data.repository.TravelRepository;
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
public final class InsertDefaultTravelUsecase_Factory implements Factory<InsertDefaultTravelUsecase> {
  private final Provider<TravelRepository> travelRepositoryProvider;

  public InsertDefaultTravelUsecase_Factory(Provider<TravelRepository> travelRepositoryProvider) {
    this.travelRepositoryProvider = travelRepositoryProvider;
  }

  @Override
  public InsertDefaultTravelUsecase get() {
    return newInstance(travelRepositoryProvider.get());
  }

  public static InsertDefaultTravelUsecase_Factory create(
      Provider<TravelRepository> travelRepositoryProvider) {
    return new InsertDefaultTravelUsecase_Factory(travelRepositoryProvider);
  }

  public static InsertDefaultTravelUsecase newInstance(TravelRepository travelRepository) {
    return new InsertDefaultTravelUsecase(travelRepository);
  }
}

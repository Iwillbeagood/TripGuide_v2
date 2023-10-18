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
public final class UpdateTravelUsecase_Factory implements Factory<UpdateTravelUsecase> {
  private final Provider<TravelRepository> travelRepositoryProvider;

  public UpdateTravelUsecase_Factory(Provider<TravelRepository> travelRepositoryProvider) {
    this.travelRepositoryProvider = travelRepositoryProvider;
  }

  @Override
  public UpdateTravelUsecase get() {
    return newInstance(travelRepositoryProvider.get());
  }

  public static UpdateTravelUsecase_Factory create(
      Provider<TravelRepository> travelRepositoryProvider) {
    return new UpdateTravelUsecase_Factory(travelRepositoryProvider);
  }

  public static UpdateTravelUsecase newInstance(TravelRepository travelRepository) {
    return new UpdateTravelUsecase(travelRepository);
  }
}

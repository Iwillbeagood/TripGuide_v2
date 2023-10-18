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
public final class GetTravelByIdUsecase_Factory implements Factory<GetTravelByIdUsecase> {
  private final Provider<TravelRepository> travelRepositoryProvider;

  public GetTravelByIdUsecase_Factory(Provider<TravelRepository> travelRepositoryProvider) {
    this.travelRepositoryProvider = travelRepositoryProvider;
  }

  @Override
  public GetTravelByIdUsecase get() {
    return newInstance(travelRepositoryProvider.get());
  }

  public static GetTravelByIdUsecase_Factory create(
      Provider<TravelRepository> travelRepositoryProvider) {
    return new GetTravelByIdUsecase_Factory(travelRepositoryProvider);
  }

  public static GetTravelByIdUsecase newInstance(TravelRepository travelRepository) {
    return new GetTravelByIdUsecase(travelRepository);
  }
}

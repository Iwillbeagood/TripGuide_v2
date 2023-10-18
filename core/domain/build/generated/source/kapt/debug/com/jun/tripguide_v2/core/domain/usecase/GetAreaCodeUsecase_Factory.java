package com.jun.tripguide_v2.core.domain.usecase;

import com.jun.tripguide_v2.core.data.repository.AreaCodeRepository;
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
public final class GetAreaCodeUsecase_Factory implements Factory<GetAreaCodeUsecase> {
  private final Provider<AreaCodeRepository> areaCodeRepositoryProvider;

  public GetAreaCodeUsecase_Factory(Provider<AreaCodeRepository> areaCodeRepositoryProvider) {
    this.areaCodeRepositoryProvider = areaCodeRepositoryProvider;
  }

  @Override
  public GetAreaCodeUsecase get() {
    return newInstance(areaCodeRepositoryProvider.get());
  }

  public static GetAreaCodeUsecase_Factory create(
      Provider<AreaCodeRepository> areaCodeRepositoryProvider) {
    return new GetAreaCodeUsecase_Factory(areaCodeRepositoryProvider);
  }

  public static GetAreaCodeUsecase newInstance(AreaCodeRepository areaCodeRepository) {
    return new GetAreaCodeUsecase(areaCodeRepository);
  }
}

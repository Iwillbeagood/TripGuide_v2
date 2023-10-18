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
public final class GetDefaultAreaCodeUsecase_Factory implements Factory<GetDefaultAreaCodeUsecase> {
  private final Provider<AreaCodeRepository> areaCodeRepositoryProvider;

  public GetDefaultAreaCodeUsecase_Factory(
      Provider<AreaCodeRepository> areaCodeRepositoryProvider) {
    this.areaCodeRepositoryProvider = areaCodeRepositoryProvider;
  }

  @Override
  public GetDefaultAreaCodeUsecase get() {
    return newInstance(areaCodeRepositoryProvider.get());
  }

  public static GetDefaultAreaCodeUsecase_Factory create(
      Provider<AreaCodeRepository> areaCodeRepositoryProvider) {
    return new GetDefaultAreaCodeUsecase_Factory(areaCodeRepositoryProvider);
  }

  public static GetDefaultAreaCodeUsecase newInstance(AreaCodeRepository areaCodeRepository) {
    return new GetDefaultAreaCodeUsecase(areaCodeRepository);
  }
}

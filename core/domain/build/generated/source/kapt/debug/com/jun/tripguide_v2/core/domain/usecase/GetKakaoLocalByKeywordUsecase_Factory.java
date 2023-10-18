package com.jun.tripguide_v2.core.domain.usecase;

import com.jun.tripguide_v2.core.data.repository.KakaoKeywordRepository;
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
public final class GetKakaoLocalByKeywordUsecase_Factory implements Factory<GetKakaoLocalByKeywordUsecase> {
  private final Provider<KakaoKeywordRepository> kakaoLocalKeywordRepositoryProvider;

  public GetKakaoLocalByKeywordUsecase_Factory(
      Provider<KakaoKeywordRepository> kakaoLocalKeywordRepositoryProvider) {
    this.kakaoLocalKeywordRepositoryProvider = kakaoLocalKeywordRepositoryProvider;
  }

  @Override
  public GetKakaoLocalByKeywordUsecase get() {
    return newInstance(kakaoLocalKeywordRepositoryProvider.get());
  }

  public static GetKakaoLocalByKeywordUsecase_Factory create(
      Provider<KakaoKeywordRepository> kakaoLocalKeywordRepositoryProvider) {
    return new GetKakaoLocalByKeywordUsecase_Factory(kakaoLocalKeywordRepositoryProvider);
  }

  public static GetKakaoLocalByKeywordUsecase newInstance(
      KakaoKeywordRepository kakaoLocalKeywordRepository) {
    return new GetKakaoLocalByKeywordUsecase(kakaoLocalKeywordRepository);
  }
}

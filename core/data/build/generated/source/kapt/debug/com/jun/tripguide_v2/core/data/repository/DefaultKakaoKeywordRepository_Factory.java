package com.jun.tripguide_v2.core.data.repository;

import com.jun.tripguide_v2.core.data.api.KakaoKeywordApi;
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
public final class DefaultKakaoKeywordRepository_Factory implements Factory<DefaultKakaoKeywordRepository> {
  private final Provider<KakaoKeywordApi> keywordApiProvider;

  public DefaultKakaoKeywordRepository_Factory(Provider<KakaoKeywordApi> keywordApiProvider) {
    this.keywordApiProvider = keywordApiProvider;
  }

  @Override
  public DefaultKakaoKeywordRepository get() {
    return newInstance(keywordApiProvider.get());
  }

  public static DefaultKakaoKeywordRepository_Factory create(
      Provider<KakaoKeywordApi> keywordApiProvider) {
    return new DefaultKakaoKeywordRepository_Factory(keywordApiProvider);
  }

  public static DefaultKakaoKeywordRepository newInstance(KakaoKeywordApi keywordApi) {
    return new DefaultKakaoKeywordRepository(keywordApi);
  }
}

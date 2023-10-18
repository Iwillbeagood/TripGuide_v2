package com.jun.tripguide_v2.core.data.di;

import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Converter;

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
public final class ApiModule_ProvideTourAreaCodeApiFactory implements Factory<TourAreaCodeApi> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Converter.Factory> converterFactoryProvider;

  public ApiModule_ProvideTourAreaCodeApiFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Converter.Factory> converterFactoryProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.converterFactoryProvider = converterFactoryProvider;
  }

  @Override
  public TourAreaCodeApi get() {
    return provideTourAreaCodeApi(okHttpClientProvider.get(), converterFactoryProvider.get());
  }

  public static ApiModule_ProvideTourAreaCodeApiFactory create(
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<Converter.Factory> converterFactoryProvider) {
    return new ApiModule_ProvideTourAreaCodeApiFactory(okHttpClientProvider, converterFactoryProvider);
  }

  public static TourAreaCodeApi provideTourAreaCodeApi(OkHttpClient okHttpClient,
      Converter.Factory converterFactory) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideTourAreaCodeApi(okHttpClient, converterFactory));
  }
}

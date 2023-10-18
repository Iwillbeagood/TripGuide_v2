package com.jun.tripguide_v2.core.data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import kotlinx.serialization.json.Json;
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
public final class ApiModule_ProvideConverterFactoryFactory implements Factory<Converter.Factory> {
  private final Provider<Json> jsonProvider;

  public ApiModule_ProvideConverterFactoryFactory(Provider<Json> jsonProvider) {
    this.jsonProvider = jsonProvider;
  }

  @Override
  public Converter.Factory get() {
    return provideConverterFactory(jsonProvider.get());
  }

  public static ApiModule_ProvideConverterFactoryFactory create(Provider<Json> jsonProvider) {
    return new ApiModule_ProvideConverterFactoryFactory(jsonProvider);
  }

  public static Converter.Factory provideConverterFactory(Json json) {
    return Preconditions.checkNotNullFromProvides(ApiModule.INSTANCE.provideConverterFactory(json));
  }
}

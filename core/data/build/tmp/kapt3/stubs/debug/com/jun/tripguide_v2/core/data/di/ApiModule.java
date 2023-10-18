package com.jun.tripguide_v2.core.data.di;

@dagger.Module
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0006H\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/jun/tripguide_v2/core/data/di/ApiModule;", "", "()V", "provideConverterFactory", "Lretrofit2/Converter$Factory;", "json", "Lkotlinx/serialization/json/Json;", "provideJson", "provideOkhttpClient", "Lokhttp3/OkHttpClient;", "provideTourAreaCodeApi", "Lcom/jun/tripguide_v2/core/data/api/TourAreaCodeApi;", "okHttpClient", "converterFactory", "data_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class ApiModule {
    @org.jetbrains.annotations.NotNull
    public static final com.jun.tripguide_v2.core.data.di.ApiModule INSTANCE = null;
    
    private ApiModule() {
        super();
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final okhttp3.OkHttpClient provideOkhttpClient() {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final retrofit2.Converter.Factory provideConverterFactory(@org.jetbrains.annotations.NotNull
    kotlinx.serialization.json.Json json) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final com.jun.tripguide_v2.core.data.api.TourAreaCodeApi provideTourAreaCodeApi(@org.jetbrains.annotations.NotNull
    okhttp3.OkHttpClient okHttpClient, @org.jetbrains.annotations.NotNull
    retrofit2.Converter.Factory converterFactory) {
        return null;
    }
    
    @dagger.Provides
    @javax.inject.Singleton
    @org.jetbrains.annotations.NotNull
    public final kotlinx.serialization.json.Json provideJson() {
        return null;
    }
}
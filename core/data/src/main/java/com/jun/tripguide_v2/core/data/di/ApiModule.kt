package com.jun.tripguide_v2.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jun.tripguide_v2.core.data.api.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.api.TourAreaBaseListApi
import com.jun.tripguide_v2.core.data.api.TourAreaCodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/B551011/KorService1/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideTourAreaCodeApi(
        retrofit: Retrofit
    ): TourAreaCodeApi {
        return retrofit.create(TourAreaCodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTourAreaBaseListApi(
        retrofit: Retrofit
    ): TourAreaBaseListApi {
        return retrofit.create(TourAreaBaseListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideKakaoLocalKeywordApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): KakaoKeywordApi {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(KakaoKeywordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
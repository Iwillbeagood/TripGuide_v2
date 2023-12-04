package com.jun.tripguide_v2.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoKeywordApi
import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoRouteAPI
import com.jun.tripguide_v2.core.data.api.tourapi.OpenTouristsApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourAreaCodeApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourCommonInfoApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourDetailIntroApi
import com.jun.tripguide_v2.core.data.api.tourapi.TourSearchKeywordApi
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

    private const val TOUR_API_BASE_URI = "http://apis.data.go.kr/B551011/KorService1/"
    private const val KAKAO_BASE_URI = "https://dapi.kakao.com/"
    private const val KAKAO_MOBILITY_URI = "https://apis-navi.kakaomobility.com/"

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
            .baseUrl(TOUR_API_BASE_URI)
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun provideTourAreaCodeApi(retrofit: Retrofit): TourAreaCodeApi =
        retrofit.create(TourAreaCodeApi::class.java)


    @Provides
    @Singleton
    fun provideTourAreaBaseListApi(retrofit: Retrofit): OpenTouristsApi =
        retrofit.create(OpenTouristsApi::class.java)


    @Provides
    @Singleton
    fun provideTourKeywordApi(retrofit: Retrofit): TourSearchKeywordApi =
        retrofit.create(TourSearchKeywordApi::class.java)

    @Provides
    @Singleton
    fun provideTourCommonInfoApi(retrofit: Retrofit): TourCommonInfoApi =
        retrofit.create(TourCommonInfoApi::class.java)

    @Provides
    @Singleton
    fun provideTourDetailIntroApi(retrofit: Retrofit): TourDetailIntroApi =
        retrofit.create(TourDetailIntroApi::class.java)

    @Provides
    @Singleton
    fun provideKakaoLocalKeywordApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): KakaoKeywordApi =
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URI)
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(KakaoKeywordApi::class.java)

    @Provides
    @Singleton
    fun provideKakaoRouteApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): KakaoRouteAPI =
        Retrofit.Builder()
            .baseUrl(KAKAO_MOBILITY_URI)
            .addConverterFactory(converterFactory)
            .client(okHttpClient).build()
            .create(KakaoRouteAPI::class.java)

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
package com.jun.tripguide_v2.core.data.api.kakaoapi

import com.jun.tripguide_v2.core.data.BuildConfig
import com.jun.tripguide_v2.core.data.api.kakaoapi.model.kakaoroute.KakaoRouteResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoRouteAPI {

    @Headers("content-type: application/json")
    @GET("v1/directions")
    suspend fun getKakaoRoute(
        @Header("Authorization") key: String = BuildConfig.KAKAO_API_KEY,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
    ): KakaoRouteResponse
}
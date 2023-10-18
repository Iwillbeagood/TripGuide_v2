package com.jun.tripguide_v2.core.data.api

import com.jun.tripguide_v2.core.data.api.model.kakaoroute.KakaoRoute
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface kakaoRouteAPI {
    @GET("v1/directions")
    suspend fun getKakaoRoute(
        @Header("Authorization") key: String,
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("priority") priority : String,
        @Query("summary") summary : Boolean
    ) : Response<KakaoRoute>
}
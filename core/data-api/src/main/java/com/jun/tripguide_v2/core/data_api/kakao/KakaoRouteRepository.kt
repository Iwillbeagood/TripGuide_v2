package com.jun.tripguide_v2.core.data_api.kakao

interface KakaoRouteRepository {

    suspend fun getRouteDuration(
        origin: String,
        destinations: String
    ): Int
}
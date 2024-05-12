package com.jun.tripguide_v2.core.data.repository.kakao

interface KakaoRouteRepository {

    suspend fun getRouteDuration(
        key: String,
        origin: String,
        destinations: String
    ): Int
}
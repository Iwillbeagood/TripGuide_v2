package com.jun.tripguide_v2.core.data.repository.kakao

import com.jun.tripguide_v2.core.data.api.kakaoapi.KakaoRouteAPI
import javax.inject.Inject

class KakaoRouteRepositoryImpl @Inject constructor(
    private val kakaoRouteAPI: KakaoRouteAPI
): KakaoRouteRepository {

    override suspend fun getRouteDuration(
        key: String,
        origin: String,
        destinations: String
    ): Int {
        return kakaoRouteAPI.getKakaoRoute(
            key = key,
            origin = origin,
            destination = destinations
        ).routes.first().summary.duration
    }
}
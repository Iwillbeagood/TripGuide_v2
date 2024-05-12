package com.jun.tripguide_v2.core.domain.usecase.kakao

import com.jun.tripguide_v2.core.data.repository.kakao.KakaoRouteRepository
import com.jun.tripguide_v2.core.domain.BuildConfig
import com.jun.tripguide_v2.core.model.Route
import java.lang.Exception
import javax.inject.Inject

class GetKakaoRouteDurationUsecase @Inject constructor(
    private val kakaoRouteRepository: KakaoRouteRepository
) {

    suspend operator fun invoke(origin: Route, destinations: Route): Int {
        return try {
            kakaoRouteRepository.getRouteDuration(
                key = BuildConfig.KAKAO_API_KEY,
                origin = origin.toPoint(),
                destinations = destinations.toPoint()
            )
        } catch (e : Exception) {
            return 0
        }
    }

    private fun Route.toPoint() = "${mapX},${mapY}"
}
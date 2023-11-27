package com.jun.tripguide_v2.core.domain.usecase.route

import com.jun.tripguide_v2.core.domain.usecase.kakao.GetKakaoRouteDurationUsecase
import com.jun.tripguide_v2.core.model.Route
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class SetTimeUsecase @Inject constructor(
    private val getKakaoRouteDurationUsecase: GetKakaoRouteDurationUsecase,
) {

    suspend operator fun invoke(startTime: LocalTime, routes: List<Route>): List<Route> {
        val setTimedRoutes = routes.toMutableList()
        var time = startTime
        var days = START_DAY_VALUE

        setTimedRoutes[0] = setTimedRoutes[0].copy(
            time = startTime,
            day = days
        )

        time = time.plusSeconds(getKakaoRouteDurationUsecase(routes[0], routes[1]).toLong())
        setTimedRoutes[1] = setTimedRoutes[1].copy(
            time = time.truncatedTo(ChronoUnit.MINUTES),
            day = days
        )

        for (i in 1 until routes.size - 2) {
            val duration = getKakaoRouteDurationUsecase(routes[i], routes[i + 1])

            time = if (time.plusHours(2) >= END_TIME_VALUE) {
                days++
                START_TIME_VALUE
            } else {
                time.plusSeconds(duration.toLong()).plusHours(2)
            }

            setTimedRoutes[i + 1] = setTimedRoutes[i + 1].copy(
                time = time.truncatedTo(ChronoUnit.MINUTES),
                day = days
            )
        }

        time = time.plusSeconds(getKakaoRouteDurationUsecase(routes[routes.lastIndex - 1], routes[routes.lastIndex]).toLong())
        setTimedRoutes[routes.lastIndex] = setTimedRoutes[routes.lastIndex].copy(
            time = time.truncatedTo(ChronoUnit.MINUTES),
            day = days
        )

        return setTimedRoutes
    }

    companion object {
        private const val START_DAY_VALUE = 1
        private val START_TIME_VALUE = LocalTime.of(10, 0)
        private val END_TIME_VALUE = LocalTime.of(20, 0)
    }
}
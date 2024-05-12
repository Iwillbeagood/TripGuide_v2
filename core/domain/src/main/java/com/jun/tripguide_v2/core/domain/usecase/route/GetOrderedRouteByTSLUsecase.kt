package com.jun.tripguide_v2.core.domain.usecase.route

import android.util.Log
import com.jun.tripguide_v2.core.domain.usecase.kakao.GetKakaoRouteDurationUsecase
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class GetOrderedRouteByTSLUsecase @Inject constructor(
    private val getKakaoRouteDurationUsecase: GetKakaoRouteDurationUsecase,
) {

    suspend operator fun invoke(origin: Route, routes: List<Route>): List<Route> {
        val (n, array) = getAllDurationOfRoute(routes.toMutableList().apply { add(0, origin) })
        val path = GetPath(n, array).getPath(0, 1)
        return getOrderedRoute(origin, routes, path)
    }

    private suspend fun getAllDurationOfRoute(routes: List<Route>): Pair<Int, Array<IntArray>> {
        val n = routes.size
        val array = Array(n) { IntArray(n) }

        for (from in 0 until n) {
            for (to in 0 until n) {
                if (from == to) continue
                array[from][to] = getKakaoRouteDurationUsecase(routes[from], routes[to])
            }
        }

        return n to array
    }

    private fun getOrderedRoute(
        origin: Route,
        routes: List<Route>,
        path: List<Int>
    ): List<Route> {
        val orderedRoute = mutableListOf<Route>()

        orderedRoute.add(origin)

        routes.mapIndexed { index, route ->
            route.copy(
                orderNum = path[index + 1]
            )
        }.let {
            orderedRoute.addAll(it)
        }

        orderedRoute.add(origin.copy(orderNum = orderedRoute.size))
        return orderedRoute
    }
}

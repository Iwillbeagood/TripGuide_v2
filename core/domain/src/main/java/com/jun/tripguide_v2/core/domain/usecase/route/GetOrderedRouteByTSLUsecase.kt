package com.jun.tripguide_v2.core.domain.usecase.route

import com.jun.tripguide_v2.core.domain.usecase.kakao.GetKakaoRouteDurationUsecase
import com.jun.tripguide_v2.core.model.Route
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

class GetOrderedRouteByTSLUsecase @Inject constructor(
    private val getKakaoRouteDurationUsecase: GetKakaoRouteDurationUsecase,
) {

    suspend operator fun invoke(origin: Route, routes: List<Route>): List<Route> {
        getAllDurationOfRoute(origin, routes)
        return getOrderedRoute(origin, routes, getRouteOrder(0, 1, mutableListOf()))
    }

    private fun getOrderedRoute(
        origin: Route,
        routes: List<Route>,
        routeOrder: List<Int>
    ): List<Route> {
        val orderedRoute = mutableListOf<Route>()

        orderedRoute.add(origin)

        routes.mapIndexed { index, route ->
            route.copy(
                orderNum = routeOrder[index]
            )
        }.let {
            orderedRoute.addAll(it)
        }

        orderedRoute.add(origin.copy(orderNum = orderedRoute.size))
        return orderedRoute
    }

    private val inf = 987654321
    private val dp = Array(16) { IntArray(1 shl 16) { -1 } }
    private val path = Array(16) { IntArray(1 shl 16) { -1 } }

    private suspend fun getAllDurationOfRoute(origin: Route, destinations: List<Route>) {
        val queue: Queue<Route> = LinkedList()
        queue.add(origin)
        queue.addAll(destinations)
        val n = queue.size

        val array = Array(n) { IntArray(n) }

        repeat(n) {
            val tempOrigin = queue.poll() as Route
            val tempDestination = queue.toList()

            tempDestination.forEach { destination ->
                val from = tempOrigin.orderNum
                val to = destination.orderNum
                array[from][to] = getKakaoRouteDurationUsecase(tempOrigin, destination)
            }

            queue.add(tempOrigin)
        }

        dfs(n, 0, 1, (1 shl n) - 1, array)
    }

    private fun dfs(n: Int, node: Int, state: Int, endState: Int, graph: Array<IntArray>): Int {
        if (state == endState) {
            return if (graph[node][0] != 0) {
                graph[node][0]
            } else {
                inf
            }
        }

        if (dp[node][state] != -1) return dp[node][state]

        dp[node][state] = inf
        for (i in 0 until n) {
            if (state and (1 shl i) != 0 || graph[node][i] == 0) continue

            val cost = graph[node][i] + dfs(n, i, state or (1 shl i), endState, graph)
            if (cost < dp[node][state]) {
                dp[node][state] = cost
                path[node][state] = i
            }
        }

        return dp[node][state]
    }

    private fun getRouteOrder(node: Int, state: Int, routeOrder: MutableList<Int>): List<Int> {
        val nextNode = path[node][state]
        if (nextNode != -1) {
            routeOrder.add(nextNode)
            getRouteOrder(nextNode, state or (1 shl nextNode), routeOrder)
        }

        return routeOrder
    }
}
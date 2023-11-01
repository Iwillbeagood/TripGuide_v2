package com.jun.tripguide_v2.core.data.repository.room

import com.jun.tripguide_v2.core.model.Route

interface RouteRepository {

    suspend fun insertRouteAll(routeList: List<Route>)

    suspend fun upDateRoute(routeList: List<Route>)

    suspend fun getTravelRoute(id: String): List<Route>
}
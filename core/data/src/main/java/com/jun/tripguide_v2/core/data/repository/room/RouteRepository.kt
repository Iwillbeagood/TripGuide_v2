package com.jun.tripguide_v2.core.data.repository.room

import com.jun.tripguide_v2.core.model.Route

interface RouteRepository {

    suspend fun insertRouteAll(routes: List<Route>)

    suspend fun updateRoute(routes: List<Route>)

    suspend fun getRoutes(id: String): List<Route>

    suspend fun deleteRoute(routes: List<Route>)

    suspend fun deleteAllRoute(id: String)
}
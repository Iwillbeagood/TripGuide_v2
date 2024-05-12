package com.jun.tripguide_v2.core.data.repository.room

import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2_core.database.dao.RouteDao
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor(
    private val routeDao: RouteDao
) : RouteRepository {

    override suspend fun insertRouteAll(routes: List<Route>) {
        routeDao.insertRouteAll(*routes.map { it.toData() }.toTypedArray())
    }

    override suspend fun updateRoute(routes: List<Route>) {
        routeDao.upDateRoute(*routes.map { it.toData() }.toTypedArray())
    }

    override suspend fun getRoutes(id: String): List<Route> {
        return routeDao.getTravelRoute(id).map { it.toData() }
    }

    override suspend fun deleteRoute(routes: List<Route>) {
        routeDao.deleteRoute(*routes.map { it.toData() }.toTypedArray())
    }

    override suspend fun deleteAllRoute(id: String) {
        routeDao.deleteAllRoute(id)
    }
}
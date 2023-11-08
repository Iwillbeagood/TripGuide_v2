package com.jun.tripguide_v2.core.data.repository.room

import com.jun.tripguide_v2.core.data.mapper.toData
import com.jun.tripguide_v2_core.database.dao.RouteDao
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class DefaultRouteRepository @Inject constructor(
    private val routeDao: RouteDao
) : RouteRepository {

    override suspend fun insertRouteAll(routeList: List<Route>) {
        routeDao.insertRouteAll(*routeList.map { it.toData() }.toTypedArray())
    }

    override suspend fun upDateRoute(routeList: List<Route>) {
        routeDao.upDateRoute(*routeList.map { it.toData() }.toTypedArray())
    }

    override suspend fun getTravelRoute(id: String): List<Route> {
        return routeDao.getTravelRoute(id).map { it.toData() }
    }
}
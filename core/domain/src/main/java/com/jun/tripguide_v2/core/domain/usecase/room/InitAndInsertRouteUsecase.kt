package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data_api.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class InitAndInsertRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository,
) {

    suspend operator fun invoke(travelId: String, route: List<Route>) {
        routeRepository.deleteAllRoute(travelId)
        routeRepository.insertRouteAll(route)
    }
}
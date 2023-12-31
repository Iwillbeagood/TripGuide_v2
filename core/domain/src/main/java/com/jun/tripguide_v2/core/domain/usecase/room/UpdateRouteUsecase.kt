package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class UpdateRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(route: List<Route>) {
        routeRepository.updateRoute(route)
    }
}
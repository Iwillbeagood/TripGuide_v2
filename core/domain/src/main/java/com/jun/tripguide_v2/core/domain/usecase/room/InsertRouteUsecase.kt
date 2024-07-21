package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class InsertRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository,
) {

    suspend operator fun invoke(route: List<Route>) {
        routeRepository.insertRouteAll(route)
    }
}
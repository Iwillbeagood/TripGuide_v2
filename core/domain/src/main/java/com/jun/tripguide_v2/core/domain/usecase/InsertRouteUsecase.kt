package com.jun.tripguide_v2.core.domain.usecase

import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class InsertRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(routeList: List<Route>) {
        routeRepository.insertRouteAll(routeList)
    }
}
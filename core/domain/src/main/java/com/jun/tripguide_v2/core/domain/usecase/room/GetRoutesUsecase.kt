package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class GetRoutesUsecase @Inject constructor(
    private val routeRepository: RouteRepository
){

    suspend operator fun invoke(travelId: String): List<Route> {
        return routeRepository.getRoutes(travelId)
    }
}
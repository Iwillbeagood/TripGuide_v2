package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class GetTravelRouteLastOrderUsecase @Inject constructor(
    private val routeRepository: RouteRepository
){

    suspend operator fun invoke(travelId: String): Int {
        return routeRepository.getRoutes(travelId).maxOf { it.orderNum }
    }
}
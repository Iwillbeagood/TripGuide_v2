package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class InsertTouristToRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository,
) {

    suspend operator fun invoke(travelId: String, tourist: List<Tourist>) {
        val targetNum = routeRepository.getRoutes(travelId).maxOfOrNull { it.orderNum } ?: 0

        var index = targetNum

        val newRoutes = tourist.toMutableList().map {
            it.toRoute(index++, travelId)
        }

        routeRepository.insertRouteAll(newRoutes)
    }

    private fun Tourist.toRoute(index: Int, travelId: String) = Route(
        orderNum = index,
        travelId = travelId,
        title = this.title,
        address = this.address,
        type = this.type,
        firstImage = this.firstImage,
        mapX = this.mapX,
        mapY = this.mapY
    )
}
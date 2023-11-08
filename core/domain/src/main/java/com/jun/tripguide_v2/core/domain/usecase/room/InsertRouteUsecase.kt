package com.jun.tripguide_v2.core.domain.usecase.room

import android.util.Log
import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class InsertRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository
) {

    suspend operator fun invoke(travelId: String, tourist: List<Tourist>) {
        var index = routeRepository.getTravelRoute(travelId).maxOfOrNull { it.orderNum } ?: 0

        routeRepository.insertRouteAll(tourist.map {
            Route(
                orderNum = ++index,
                travelId = travelId,
                title = it.title,
                address = it.address,
                typeId = it.typeId,
                firstImage = it.firstImage,
                mapX = it.mapX,
                mapY = it.mapY
            )
        })
    }
}
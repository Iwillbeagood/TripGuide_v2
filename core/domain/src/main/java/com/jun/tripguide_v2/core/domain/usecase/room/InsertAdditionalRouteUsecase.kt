package com.jun.tripguide_v2.core.domain.usecase.room

import android.util.Log
import com.jun.tripguide_v2.core.data.repository.room.RouteRepository
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class InsertAdditionalRouteUsecase @Inject constructor(
    private val routeRepository: RouteRepository,
) {

    suspend operator fun invoke(travelId: String, tourist: List<Tourist>, targetNum: Int) {
        updateRoutesOrderNum(
            routeRepository.getRoutes(travelId).filter { it.orderNum > targetNum },
            tourist.size
        )
        var index = targetNum + 1

        val newRoutes = tourist.toMutableList().map {
            it.toRoute(index++, travelId)
        }

        routeRepository.insertRouteAll(newRoutes)
    }

    private suspend fun updateRoutesOrderNum(afterTargetNumRoutes: List<Route>, newRoutesCnt: Int) {
        routeRepository.updateRoute(afterTargetNumRoutes.map {
            it.copy(orderNum = it.orderNum + newRoutesCnt)
        })
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
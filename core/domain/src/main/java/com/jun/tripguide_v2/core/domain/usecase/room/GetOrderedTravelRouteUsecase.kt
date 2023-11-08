package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Route
import javax.inject.Inject

class GetOrderedTravelRouteUsecase @Inject constructor(
    private val getTravelRouteUsecase: GetTravelRouteUsecase,
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
) {

    suspend operator fun invoke(travelId: String) {
        // travelId와 동일한 route를 전부 다 가져온다.
        val routes = getTravelRouteUsecase(travelId).toMutableList()
        val startingRoute = getTravelByIdUsecase(travelId).let {
            Route(
                travelId = it.travelId,
                title = it.startingPoint.name,
                mapX = it.startingPoint.x,
                mapY = it.startingPoint.y
            )
        }
        // 첫번쨰와 마지막에  travel의 시작 주소를 넣는다.
        routes.add(0, startingRoute)
        routes.add(startingRoute)


    }
}
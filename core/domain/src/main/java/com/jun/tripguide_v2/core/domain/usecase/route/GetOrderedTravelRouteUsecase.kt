/**
 * `GetOrderedTravelRouteUsecase`에서는 여행 ID에 해당하는 경로를 다른 유스케이스를 통해 가져온 다음
 * `getOrderedRouteByTSLUsecase`를 통해 외판원 문제 알고리즘을 활용하여, 최소의 이동 시간을 갖는 여행 경로를 얻습니다.
 * */

package com.jun.tripguide_v2.core.domain.usecase.route

import android.util.Log
import com.jun.tripguide_v2.core.domain.usecase.room.GetRoutesUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.GetTravelByIdUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.InsertRouteUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.UpdateRouteUsecase
import com.jun.tripguide_v2.core.domain.usecase.room.UpdateTravelUsecase
import com.jun.tripguide_v2.core.model.Route
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class GetOrderedTravelRouteUsecase @Inject constructor(
    private val insertRouteUsecase: InsertRouteUsecase,
    private val getRoutesUsecase: GetRoutesUsecase,
    private val getTravelByIdUsecase: GetTravelByIdUsecase,
    private val updateTravelUsecase: UpdateTravelUsecase,
    private val updateRouteUsecase: UpdateRouteUsecase,
    private val setTimeUsecase: SetTimeUsecase,
    private val getOrderedRouteByTSLUsecase: GetOrderedRouteByTSLUsecase
) {

    suspend operator fun invoke(travelId: String): List<Route> {
        val travel = getTravelByIdUsecase(travelId)
        val routes = getRoutesUsecase(travelId)
        val travelStartTime = travel.startTime

        if (travel.isOrdered) {
            return routes.initRoutes()
        }

        val orderedRoutes = getOrderedRouteByTSLUsecase(travel.toOriginRoute(), routes)
        val setTimeRoutes = setTimeUsecase(travelStartTime, orderedRoutes).initRoutes()
        updateTravelUsecase(travel.copy(isOrdered = true))
        updateRouteUsecase(setTimeRoutes)
        insertRouteUsecase(listOf(setTimeRoutes.first(), setTimeRoutes.last()))
        return setTimeRoutes
    }

    private fun Travel.toOriginRoute() =
        Route(
            id = null,
            travelId = travelId,
            title = startingPoint.name,
            mapX = startingPoint.x,
            mapY = startingPoint.y
        )

    private fun List<Route>.initRoutes() = sortedBy { it.orderNum }.toMutableList().apply {
        this[0] = this[0].copy(isSelected = true, isFirst = true)
        this[1] = this[1].copy(isBeforeRouteSelected = true)
        this[lastIndex] = this[lastIndex].copy(isLast = true)
    }
}
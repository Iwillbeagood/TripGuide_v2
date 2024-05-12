package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.model.Tourist
import javax.inject.Inject

class InsertTouristToRouteUsecase @Inject constructor(
    private val updateTravelUsecase: UpdateTravelUsecase,
    private val getTravelByIdUsecase: GetTravelByIdUsecase
) {

    suspend operator fun invoke(travelId: String, tourist: List<Tourist>, orderNum: Int = 0) {
        val travel = getTravelByIdUsecase(travelId)
        val places = travel.places.toMutableList()

        if (orderNum == 0) places.addAll(tourist)
        else places.addAll(orderNum + 1, tourist)

        updateTravelUsecase(
            travel.copy(
                places = places
            )
        )
    }
}
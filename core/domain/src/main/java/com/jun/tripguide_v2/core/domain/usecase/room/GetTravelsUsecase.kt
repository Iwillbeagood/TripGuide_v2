package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class GetTravelsUsecase @Inject constructor(
    private val travelRepository: TravelRepository,
    private val getTravelRouteUsecase: GetTravelRouteUsecase
) {

    suspend operator fun invoke(): List<Travel> {
        return travelRepository.getTravels().map {
            it.copy(
                images = getTravelRouteUsecase(it.travelId).filter { it.firstImage != "" }.map { route ->
                    route.firstImage
                }
            )
        }
    }
}
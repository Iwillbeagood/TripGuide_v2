package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class UpdateTravelUsecase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke(travel: Travel) {
        travelRepository.updateTravel(travel)
    }
}
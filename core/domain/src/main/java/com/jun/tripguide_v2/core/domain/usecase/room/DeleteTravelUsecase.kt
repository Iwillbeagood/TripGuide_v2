package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data_api.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class DeleteTravelUsecase @Inject constructor(
    private val travelRepository: TravelRepository,
) {

    suspend operator fun invoke(travel: Travel) {
        travelRepository.deleteTravel(travel)
    }
}
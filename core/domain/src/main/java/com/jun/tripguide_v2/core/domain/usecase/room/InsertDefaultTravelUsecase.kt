package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class InsertDefaultTravelUsecase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke(travel: Travel): Long {
        travelRepository.insertTravel(travel)
        return travel.travelId
    }
}
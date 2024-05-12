package com.jun.tripguide_v2.core.domain.usecase.room

import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class GetTravelsUsecase @Inject constructor(
    private val travelRepository: TravelRepository
) {

    suspend operator fun invoke(): List<Travel> {
        val travels = travelRepository.getTravels()

        travels.forEach {
            if (it.images.isEmpty()) {
                travelRepository.deleteTravel(it)
            }
        }

        return travels.filter {
            it.images.isNotEmpty()
        }
    }
}
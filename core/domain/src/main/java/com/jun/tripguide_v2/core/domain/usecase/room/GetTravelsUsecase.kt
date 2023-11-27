package com.jun.tripguide_v2.core.domain.usecase.room

import android.util.Log
import com.jun.tripguide_v2.core.data.repository.room.TravelRepository
import com.jun.tripguide_v2.core.model.Travel
import javax.inject.Inject

class GetTravelsUsecase @Inject constructor(
    private val travelRepository: TravelRepository,
    private val getRoutesUsecase: GetRoutesUsecase,
) {

    suspend operator fun invoke(): List<Travel> {
        val travels = travelRepository.getTravels().map { travel ->
            val routes = getRoutesUsecase(travel.travelId)
            travel.copy(
                images = routes.filter { it.firstImage != "" }.map { route ->
                    route.firstImage
                }
            )
        }

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
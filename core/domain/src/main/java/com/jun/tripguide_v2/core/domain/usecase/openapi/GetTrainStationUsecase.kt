package com.jun.tripguide_v2.core.domain.usecase.openapi

import com.jun.tripguide_v2.core.data.repository.trainapi.TrainStationRepository
import com.jun.tripguide_v2.core.domain.BuildConfig
import com.jun.tripguide_v2.core.model.TrainStation
import javax.inject.Inject

class GetTrainStationUsecase @Inject constructor(
    private val trainStationRepository: TrainStationRepository
) {

    suspend operator fun invoke(cityCode: Int): List<TrainStation> {
        return trainStationRepository.getTrainStations(
            serviceKey = BuildConfig.OPEN_API_KEY,
            cityCode = cityCode
        )
    }
}
package com.jun.tripguide_v2.core.data.repository.trainapi

import com.jun.tripguide_v2.core.data.api.trainapi.TrainStationApi
import com.jun.tripguide_v2.core.data.mapper.toTrainStation
import com.jun.tripguide_v2.core.data_api.trainapi.TrainStationRepository
import com.jun.tripguide_v2.core.model.TrainStation
import javax.inject.Inject

class TrainStationRepositoryImpl @Inject constructor(
    private val trainStationApi: TrainStationApi
) : TrainStationRepository {

    override suspend fun getTrainStations(cityCode: Int): List<TrainStation> {
        return trainStationApi.getTrainStations(
            cityCode = cityCode.toString()).response.body.items.item.map { it.toTrainStation() }
    }
}
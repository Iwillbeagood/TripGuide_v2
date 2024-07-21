package com.jun.tripguide_v2.core.data_api.repository.trainapi

import com.jun.tripguide_v2.core.model.TrainStation

interface TrainStationRepository {

    suspend fun getTrainStations(cityCode: Int): List<TrainStation>
}
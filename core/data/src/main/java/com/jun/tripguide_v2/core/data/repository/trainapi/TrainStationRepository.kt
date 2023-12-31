package com.jun.tripguide_v2.core.data.repository.trainapi

import com.jun.tripguide_v2.core.model.TrainStation

interface TrainStationRepository {

    suspend fun getTrainStations(serviceKey: String, cityCode: Int): List<TrainStation>
}
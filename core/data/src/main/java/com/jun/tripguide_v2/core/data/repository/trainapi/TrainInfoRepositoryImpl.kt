package com.jun.tripguide_v2.core.data.repository.trainapi

import com.jun.tripguide_v2.core.data.api.trainapi.TrainInfoApi
import com.jun.tripguide_v2.core.data.mapper.toTrainInfo
import com.jun.tripguide_v2.core.data_api.repository.trainapi.TrainInfoRepository
import com.jun.tripguide_v2.core.model.TrainInfo
import javax.inject.Inject

class TrainInfoRepositoryImpl @Inject constructor(
    private val trainInfoApi: TrainInfoApi
) : TrainInfoRepository {

    override suspend fun getTrainInfo(
        depPlaceId: String,
        arrPlaceId: String,
        depPlanedTime: String
    ): List<TrainInfo> {
        return try {
            trainInfoApi.getTrainStations(
                depPlaceId = depPlaceId,
                arrPlaceId = arrPlaceId,
                depPlanedTime = depPlanedTime
            ).response.body.items.item.map {
                it.toTrainInfo()
            }
        } catch (i: Exception) {
            emptyList()
        }
    }
}
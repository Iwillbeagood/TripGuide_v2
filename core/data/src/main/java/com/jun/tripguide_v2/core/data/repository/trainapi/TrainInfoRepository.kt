package com.jun.tripguide_v2.core.data.repository.trainapi

import com.jun.tripguide_v2.core.model.TrainInfo

interface TrainInfoRepository {

    suspend fun getTrainInfo(
        serviceKey: String,
        depPlaceId: String,
        arrPlaceId: String,
        depPlanedTime: String
    ): List<TrainInfo>
}
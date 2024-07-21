package com.jun.tripguide_v2.core.data_api.repository.trainapi

import com.jun.tripguide_v2.core.model.TrainInfo

interface TrainInfoRepository {

    suspend fun getTrainInfo(
        depPlaceId: String,
        arrPlaceId: String,
        depPlanedTime: String
    ): List<TrainInfo>
}
package com.jun.tripguide_v2.core.domain.usecase.openapi

import com.jun.tripguide_v2.core.data.repository.trainapi.TrainInfoRepository
import com.jun.tripguide_v2.core.domain.BuildConfig
import com.jun.tripguide_v2.core.model.TrainInfo
import javax.inject.Inject

class GetTrainInfoUsecase @Inject constructor(
    private val trainInfoRepository: TrainInfoRepository
) {

    suspend operator fun invoke(
        depPlaceId: String,
        arrPlaceId: String,
        depPlanedTime: String
    ): List<TrainInfo> {
        return trainInfoRepository.getTrainInfo(
            serviceKey = BuildConfig.OPEN_API_KEY,
            depPlaceId = depPlaceId,
            arrPlaceId = arrPlaceId,
            depPlanedTime = depPlanedTime
        )
    }
}
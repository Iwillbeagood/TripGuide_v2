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
        ).let {
            when (cityCode) {
                INCHEON -> {
                    // 인천의 경우에는 경기 추가
                    it + trainStationRepository.getTrainStations(
                        serviceKey = BuildConfig.OPEN_API_KEY,
                        cityCode = GYEONGGI
                    )
                }
                SEJONG -> {
                    // 세종의 경우에는 충북 추가
                    it + trainStationRepository.getTrainStations(
                        serviceKey = BuildConfig.OPEN_API_KEY,
                        cityCode = CHUNGBUK
                    )
                }
                else -> {
                    it
                }
            }
        }.sortedBy { it.stationName }
    }

    companion object {
        const val INCHEON = 23
        const val SEJONG = 12

        const val GYEONGGI = 31
        const val CHUNGBUK = 33
    }
}
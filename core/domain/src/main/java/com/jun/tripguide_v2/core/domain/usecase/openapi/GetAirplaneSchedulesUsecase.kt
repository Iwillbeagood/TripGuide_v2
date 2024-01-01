package com.jun.tripguide_v2.core.domain.usecase.openapi

import com.jun.tripguide_v2.core.model.AirplaneSchedule
import com.jun.tripguide_v2.core.data.repository.airplaneapi.AirplaneScheduleRepository
import com.jun.tripguide_v2.core.domain.BuildConfig
import javax.inject.Inject

class GetAirplaneSchedulesUsecase @Inject constructor(
    private val airplaneScheduleRepository: AirplaneScheduleRepository
) {

    suspend operator fun invoke(
        departCityCode: String = "GMP",
        arriveCityCode: String = "PUS",
        pageNo: String = "1"
    ): List<AirplaneSchedule> {
        return airplaneScheduleRepository.getAirplaneSchedule(
            serviceKey = BuildConfig.OPEN_API_KEY,
            departCityCode = departCityCode,
            arriveCityCode = arriveCityCode,
            pageNo = pageNo
        )
    }
}
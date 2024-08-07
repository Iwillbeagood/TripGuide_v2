package com.jun.tripguide_v2.core.domain.usecase.openapi

import com.jun.tripguide_v2.core.data_api.repository.airplaneapi.AirplaneScheduleRepository
import com.jun.tripguide_v2.core.model.AirplaneSchedule
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
            departCityCode = departCityCode,
            arriveCityCode = arriveCityCode,
            pageNo = pageNo
        )
    }
}
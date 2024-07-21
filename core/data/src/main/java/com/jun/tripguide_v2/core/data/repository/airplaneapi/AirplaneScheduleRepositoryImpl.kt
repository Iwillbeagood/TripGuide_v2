package com.jun.tripguide_v2.core.data.repository.airplaneapi

import com.jun.tripguide_v2.core.model.AirplaneSchedule
import com.jun.tripguide_v2.core.data.api.airplaneapi.AirplaneSchedulesApi
import com.jun.tripguide_v2.core.data.mapper.toAirplaneSchedule
import com.jun.tripguide_v2.core.data_api.airplaneapi.AirplaneScheduleRepository
import javax.inject.Inject

class AirplaneScheduleRepositoryImpl @Inject constructor(
    private val airplaneSchedulesApi: AirplaneSchedulesApi
) : AirplaneScheduleRepository {

    override suspend fun getAirplaneSchedule(
        departCityCode: String,
        arriveCityCode: String,
        pageNo: String
    ): List<AirplaneSchedule> {
        return airplaneSchedulesApi.getAirplaneSchedules(
            departCityCode, arriveCityCode, pageNo
        ).response.body.airplaneScheduleItems.map { it.toAirplaneSchedule() }
    }
}
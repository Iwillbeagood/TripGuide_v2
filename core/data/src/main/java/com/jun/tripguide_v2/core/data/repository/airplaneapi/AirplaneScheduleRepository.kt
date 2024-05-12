package com.jun.tripguide_v2.core.data.repository.airplaneapi

import com.jun.tripguide_v2.core.model.AirplaneSchedule

interface AirplaneScheduleRepository {

    suspend fun getAirplaneSchedule(
        serviceKey: String,
        departCityCode: String,
        arriveCityCode: String,
        pageNo: String
    ): List<AirplaneSchedule>
}
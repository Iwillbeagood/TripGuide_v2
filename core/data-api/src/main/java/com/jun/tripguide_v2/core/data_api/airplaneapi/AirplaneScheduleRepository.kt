package com.jun.tripguide_v2.core.data_api.airplaneapi

import com.jun.tripguide_v2.core.model.AirplaneSchedule

interface AirplaneScheduleRepository {

    suspend fun getAirplaneSchedule(
        departCityCode: String,
        arriveCityCode: String,
        pageNo: String
    ): List<AirplaneSchedule>
}
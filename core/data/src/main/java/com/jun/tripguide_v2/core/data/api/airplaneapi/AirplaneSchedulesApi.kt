package com.jun.tripguide_v2.core.data.api.airplaneapi

import com.jun.tripguide_v2.core.data.BuildConfig
import com.jun.tripguide_v2.core.data.api.airplaneapi.model.AirplaneScheduleResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AirplaneSchedulesApi {

    @GET("getDflightScheduleList")
    suspend fun getAirplaneSchedules(
        @Query("schDeptCityCode") departCityCode: String,
        @Query("schArrvCityCode") arriveCityCode: String,
        @Query("pageNo") pageNo: String,
        @Query("_type") type: String = "json",
        @Header("ServiceKey") serviceKey: String = BuildConfig.OPEN_API_KEY,
    ): AirplaneScheduleResponse
}
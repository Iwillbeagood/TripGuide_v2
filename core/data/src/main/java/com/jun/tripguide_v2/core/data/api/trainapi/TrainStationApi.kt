package com.jun.tripguide_v2.core.data.api.trainapi

import com.jun.tripguide_v2.core.data.api.trainapi.model.station.TrainStationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainStationApi {

    @GET("getCtyAcctoTrainSttnList")
    suspend fun getTrainStations(
        @Query("serviceKey") serviceKey: String,
        @Query("numOfRows") numOfRows: String = "50",
        @Query("_type") type: String = "json",
        @Query("cityCode") cityCode: String,
    ): TrainStationResponse
}
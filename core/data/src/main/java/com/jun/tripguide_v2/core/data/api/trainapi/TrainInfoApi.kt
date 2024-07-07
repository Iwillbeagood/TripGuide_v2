package com.jun.tripguide_v2.core.data.api.trainapi

import com.jun.tripguide_v2.core.data.BuildConfig
import com.jun.tripguide_v2.core.data.api.trainapi.model.info.TrainInfoResponse
import com.jun.tripguide_v2.core.data.api.trainapi.model.station.TrainStationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TrainInfoApi {

    @GET("getStrtpntAlocFndTrainInfo")
    suspend fun getTrainStations(
        @Query("serviceKey") serviceKey: String = BuildConfig.OPEN_API_KEY,
        @Query("numOfRows") numOfRows: String = "40",
        @Query("_type") type: String = "json",
        @Query("depPlaceId") depPlaceId: String,
        @Query("arrPlaceId") arrPlaceId: String,
        @Query("depPlandTime") depPlanedTime: String,
    ): TrainInfoResponse
}
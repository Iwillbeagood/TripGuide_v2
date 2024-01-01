package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.location_tourist.LocationTouristResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourLocationTouristApi {

    @GET("locationBasedList1")
    suspend fun getLocationBasedTourists(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: Int,
        @Query("mapX") mapX: Double,
        @Query("mapY") mapY: Double,
        @Query("radius") radius: Int = 200000
    ): LocationTouristResponse
}
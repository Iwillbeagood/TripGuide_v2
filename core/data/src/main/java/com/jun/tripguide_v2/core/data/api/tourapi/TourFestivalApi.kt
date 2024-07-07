package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.festival.FestivalResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourFestivalApi {

    @GET("searchFestival1")
    suspend fun getFestivals(
        @Query("pageNo") pageNo: Int,
        @Query("arrange") arrange: String = "P",
        @Query("eventStartDate") eventStartDate: String,
        @QueryMap queryParams: Map<String, String> = Const.queryParams,
    ): FestivalResponse
}
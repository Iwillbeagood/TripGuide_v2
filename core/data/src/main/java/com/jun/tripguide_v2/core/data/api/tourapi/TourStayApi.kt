package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.stay.StayResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourStayApi {

    @GET("searchStay1")
    suspend fun getStays(
        @Query("pageNo") pageNo: Int,
        @Query("arrange") arrange: String = "P",
        @QueryMap queryParams: Map<String, String> = Const.queryParams,
    ): StayResponse
}
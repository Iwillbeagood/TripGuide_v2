package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.tourist.TouristResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface OpenTouristsApi {

    @GET("areaBasedList1")
    suspend fun getTourists(
        @QueryMap queryParams: Map<String, String>,
        @Query("pageNo") pageNo: String,
        @Query("arrange") arrange: String,
        @Query("areaCode") areaCode: String,
        @Query("sigunguCode") sigunguCode: String? = null,
        @Query("contentTypeId") contentType: String? = null
    ): TouristResponse
}
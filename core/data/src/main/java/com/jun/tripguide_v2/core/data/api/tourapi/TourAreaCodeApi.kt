package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.areacode.AreaCodeResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourAreaCodeApi {
    @GET("areaCode1")
    suspend fun getAreaCode(
        @QueryMap queryParams: Map<String, String>,
        @Query("areaCode") areaCode: String? = null
    ): AreaCodeResponse
}
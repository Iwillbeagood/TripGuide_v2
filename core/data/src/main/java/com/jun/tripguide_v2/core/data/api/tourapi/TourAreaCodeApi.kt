package com.jun.tripguide_v2.core.data.api.tourapi

import com.jun.tripguide_v2.core.data.api.tourapi.model.areacode.AreaCodeResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TourAreaCodeApi {
    @GET("areaCode1")
    suspend fun getAreaCode(
        @Query("areaCode") areaCode: String? = null,
        @QueryMap queryParams: Map<String, String> = Const.queryParams,
    ): AreaCodeResponse
}